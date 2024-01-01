package be.kdg.project.repository;

import be.kdg.project.domain.Channel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

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
                        rs.getDate("date").toLocalDate(),
                        rs.getInt("subscribers")
                ));
    }

    @Override
    public Channel createChannel(Channel channel) {
        Number generatedKey = channelInserter.executeAndReturnKey(Map.of(
                "name", channel.getName(),
                //"date", channel.getDate(),
                "subscribers", channel.getSubscribers()
        ));

        //channel.setId(generatedKey.intValue());

        return channel;
    }

    @Override
    public List<Channel> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM CHANNELS WHERE NAME = ?",
                (rs, rowNum) -> new Channel(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("date").toLocalDate(),
                        rs.getInt("subscribers")),
                name);
    }

    @Override
    public Channel findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM CHANNELS WHERE ID = ?",
                this::mapRow,
                id);
    }

    @Override
    public Channel save(Channel channel) {
//        PreparedStatementCreatorFactory pscf = new
//                PreparedStatementCreatorFactory("INSERT INTO CHANNELS(ID, NAME,DATE, SUBSCRIBERS) VALUES (?, ?, ?,?)",
//                Types.INTEGER,
//                Types.VARCHAR,
//                Types.DATE,
//                Types.INTEGER);
//        pscf.setReturnGeneratedKeys(true);
//        PreparedStatementCreator psc =
//                pscf.newPreparedStatementCreator(List.of(channel.getName(),
//                        channel.getDate(),
//                        channel.getSubscribers()));
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(psc, keyHolder);
//        channel.setId(keyHolder.getKey().intValue());
//        return channel;
        String sqlQuery = "INSERT INTO CHANNELS(ID, NAME, DATE, SUBSCRIBERS) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlQuery,
                channel.getId(),
                channel.getName(),
                channel.getDate(),
                channel.getSubscribers());
        return channel;
    }

    private Channel mapRow(ResultSet rs, int i) throws SQLException {
        return new Channel(rs.getInt("id"),
                rs.getString("name"),
                rs.getDate("date").toLocalDate(),
                rs.getInt("subscribers"));
    }

}
