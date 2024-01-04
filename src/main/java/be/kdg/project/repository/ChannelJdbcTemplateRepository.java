package be.kdg.project.repository;

import be.kdg.project.domain.Channel;
import be.kdg.project.domain.Video;
import be.kdg.project.domain.VideoGenre;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ChannelJdbcTemplateRepository implements ChannelRepositoryJdbc {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert channelInserter;

    public ChannelJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        channelInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("CHANNELS")
                .usingGeneratedKeyColumns("ID");
    }


    @Override
    public List<Channel> showAllChannels() {
        return jdbcTemplate.query("SELECT * FROM CHANNELS",
                (rs, rowNum) -> new Channel(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("date") == null ? LocalDate.now() : rs.getDate("date").toLocalDate(),
                        rs.getInt("subscribers")
                ));
    }

    @Override
    public List<Video> showVideosByChannelId(Long channelId) {
        String sql = "SELECT V.* " +
                "FROM VIDEOS V " +
                "INNER JOIN CHANNEL_VIDEO_RELATION CVR ON V.ID = CVR.VIDEO_ID " +
                "WHERE CVR.CHANNEL_ID = ?";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Video(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("views"),
                        rs.getString("link"),
                        VideoGenre.valueOf(rs.getString("genre"))
                ),
                channelId);
    }

    @Override
    public List<Channel> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM CHANNELS WHERE NAME = ?",
                (rs, rowNum) -> new Channel(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("date") == null ? LocalDate.now() : rs.getDate("date").toLocalDate(),
                        rs.getInt("subscribers")),
                name);
    }


    @Override
    public Channel findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM CHANNELS WHERE ID = ?",
                this::mapRow,
                id);
    }

    @Override
    public Channel save(Channel channel) {
        String sqlQuery = "INSERT INTO CHANNELS( NAME, DATE, SUBSCRIBERS) " +
                "VALUES ( ?, ?, ?)";
        jdbcTemplate.update(sqlQuery,
                // channel.getId(),
                channel.getName(),
                channel.getDate(),
                channel.getSubscribers());
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("channels")
                .usingGeneratedKeyColumns("id");
        return channel;
    }

    @Override
    @Transactional
    public Channel deleteById(Long id) {
        Channel channelToDelete = findById(id);

        if (channelToDelete != null) {
            jdbcTemplate.update("DELETE FROM CHANNEL_VIDEO_RELATION WHERE CHANNEL_ID = ?", id);
            jdbcTemplate.update("DELETE FROM CHANNELS WHERE id = ?", id);

            return channelToDelete;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel with ID " + id + " not found");
        }
    }
    private void deleteVideosForChannel(Long channelId) {
        jdbcTemplate.update("DELETE FROM CHANNEL_VIDEO_RELATION WHERE CHANNEL_ID = ?", channelId);
    }

    private Channel mapRow(ResultSet rs, int i) throws SQLException {
        return new Channel(rs.getInt("id"),
                rs.getString("name"),
                rs.getDate("date").toLocalDate(),
                rs.getInt("subscribers"));
    }
}
