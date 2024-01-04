package be.kdg.project.repository;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Video;
import be.kdg.project.domain.VideoGenre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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
                // channel.getId(),
                video.getTitle(),
                video.getViews(),
                video.getLink(),
                video.getGenre().toString());
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("channels")
                .usingGeneratedKeyColumns("id");
        return video;
    }

    @Override
    public Video deleteById(Long id) {
        return null;
    }
}
