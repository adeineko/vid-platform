package be.kdg.project.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class JdbcTemplateConfig {
    @Bean
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder
                .create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql:jdbc_prog3")
                .username("postgres")
                .password("12345")
                .build();
        return dataSource;
    }
}
