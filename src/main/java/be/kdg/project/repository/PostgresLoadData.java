package be.kdg.project.repository;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class PostgresLoadData {
    private static final Logger log = LoggerFactory.getLogger(PostgresLoadData.class);

    private JdbcTemplate jdbcTemplate;

    public PostgresLoadData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @PostConstruct
    public void loadData() {
        jdbcTemplate.update("DROP TABLE IF EXISTS CHANNEL_VIDEO_RELATION;");
        jdbcTemplate.update("DROP TABLE IF EXISTS CHANNELS");
        jdbcTemplate.update("DROP TABLE IF EXISTS VIDEOS");

        jdbcTemplate.update("""
                 CREATE TABLE CHANNELS
                      (
                          ID          SERIAL PRIMARY KEY,
                          NAME        CHARACTER VARYING(100) NOT NULL,
                          DATE        DATE,
                          SUBSCRIBERS INTEGER
                      );
                """);
        jdbcTemplate.update("""
                      INSERT INTO CHANNELS(NAME, DATE, SUBSCRIBERS)
                                         VALUES ('Beyond Fireship', '2022-09-04', '329000'),
                                                ('Tech Acad', '2017-03-28', '75000');
                """);

        jdbcTemplate.update("""
                 CREATE TABLE VIDEOS
                        (
                            ID   SERIAL PRIMARY KEY,
                            TITLE CHARACTER VARYING(100) NOT NULL,
                            VIEWS INTEGER,
                            LINK  VARCHAR,
                            GENRE VARCHAR(50),
                            CHANNEL_ID VARCHAR
                        );
                """);
        jdbcTemplate.update("""
                      INSERT INTO VIDEOS(TITLE, VIEWS, LINK, GENRE)
                        VALUES ('Next.js 13-The Basics', '512000', 'https://youtu.be/__mSgDEOyv8?si=PzO5bV1nShiWz30p', 'Educational'),
                        ('Time is Relative, even in JS', '96000', 'https://youtu.be/acemrBKuDqw?si=0pDWXFSWkD6oMq0A', 'Educational');
                """);

        jdbcTemplate.update("""
                CREATE TABLE CHANNEL_VIDEO_RELATION
                          (
                              CHANNEL_ID INTEGER,
                              VIDEO_ID   INTEGER,
                              CONSTRAINT FK_CHANNEL_ID
                                  FOREIGN KEY (CHANNEL_ID) REFERENCES CHANNELS,
                              CONSTRAINT FK_VIDEO_ID
                                  FOREIGN KEY (VIDEO_ID) REFERENCES VIDEOS
                          );
                """);
        jdbcTemplate.update("""
                      INSERT INTO PUBLIC.CHANNEL_VIDEO_RELATION (CHANNEL_ID, VIDEO_ID) VALUES (1, 1);
                      INSERT INTO PUBLIC.CHANNEL_VIDEO_RELATION (CHANNEL_ID, VIDEO_ID) VALUES (1, 2);
                """);
    }
}
