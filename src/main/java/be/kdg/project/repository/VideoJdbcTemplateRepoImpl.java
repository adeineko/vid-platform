package be.kdg.project.repository;

import be.kdg.project.domain.Video;
import be.kdg.project.domain.VideoGenre;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VideoJdbcTemplateRepoImpl implements VideoRepositoryJdbc {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert videoInserter;

    public VideoJdbcTemplateRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.videoInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("VIDEOS").usingGeneratedKeyColumns("ID");

    }

    @Override
    public List<Video> showAllVideos() {
        return jdbcTemplate.query("SELECT * FROM VIDEOS",
                (rs, rowNum) -> new Video(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("views"),
                        rs.getString("link"),
                        VideoGenre.valueOf(rs.getString("genre"))
                ));
    }

    @Override
    public List<Video> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM VIDEOS WHERE NAME = ?",
                (rs, rowNum) -> new Video(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("views"),
                        rs.getString("link"),
                        VideoGenre.valueOf(rs.getString("genre"))
                ));
    }

    @Override
    public Video findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM VIDEOS WHERE ID = ?",
                this::mapRow,
                id);
    }

    private Video mapRow(ResultSet rs, int i) throws SQLException {
        return new Video(rs.getInt("id"),
                rs.getString("title"),
                rs.getInt("views"),
                rs.getString("link"),
                VideoGenre.valueOf(rs.getString("genre")));
    }

    @Override
    public Video save(Video video) {
        String sqlQuery = "INSERT INTO VIDEOS( TITLE, VIEWS, LINK, GENRE) " +
                "VALUES ( ?, ?, ?, ?)";
        jdbcTemplate.update(sqlQuery,
                video.getTitle(),
                video.getViews(),
                video.getLink(),
                video.getGenre().getDisplayValue()
        );

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("videos")
                .usingGeneratedKeyColumns("id");
//        String sqlQuery2 = "INSERT INTO CHANNEL_VIDEO_RELATION(VIDEO_ID, CHANNEL_ID)" +
//                "VALUES (?,?)";
//        jdbcTemplate.update(sqlQuery2,
//                video.getId(),
//                video.getChannel().getId()
//        );
        return video;
    }
//    public Video saveChId(Video video) {
//        String sqlQuery2 = "INSERT INTO CHANNEL_VIDEO_RELATION(VIDEO_ID)" +
//                "VALUES (?)";
//        jdbcTemplate.update(sqlQuery2,
//                video.getId()
//               // video.getChannel()
//        );
//        return video;
//    }


    @Override
    public Video deleteById(Long id) {
        Video videoToDelete = findById(id);

        if (videoToDelete != null) {
            jdbcTemplate.update("DELETE FROM CHANNEL_VIDEO_RELATION WHERE VIDEO_ID = ?", id);
            jdbcTemplate.update("DELETE FROM VIDEOS WHERE id = ?", id);

            return videoToDelete;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video with ID " + id + " not found");
        }
    }
}
