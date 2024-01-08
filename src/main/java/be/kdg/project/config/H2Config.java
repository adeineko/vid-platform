package be.kdg.project.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
@Configuration
@Profile("dev")
public class H2Config {
    @Bean
    public DataSource dataSource(){
        DataSource dataSource = DataSourceBuilder
                .create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:studentdb")
                .username("sa")
                .password("")
                .build();
        return dataSource;
    }
}
