package be.kdg.project.repository;

import be.kdg.project.domain.Channel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import org.springframework.stereotype.Repository;

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
    public Channel deleteById(Long id) {
        String sqlQuery = "DELETE FROM CHANNELS WHERE ID = ?";

        int deletedRows = jdbcTemplate.update(sqlQuery, id);

        // Check if any rows were deleted
        if (deletedRows > 0) {
            // If rows were deleted, return the deleted channel
            Channel deletedChannel = findById(id);
            return deletedChannel;
        } else {
            // If no rows were deleted, return null or throw an exception, depending on your requirements
            return null;
        }
    }

    private Channel mapRow(ResultSet rs, int i) throws SQLException {
        return new Channel(rs.getInt("id"),
                rs.getString("name"),
                rs.getDate("date").toLocalDate(),
                rs.getInt("subscribers"));
    }


}
