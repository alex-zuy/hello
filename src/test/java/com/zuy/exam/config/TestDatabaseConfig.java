
package com.zuy.exam.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class TestDatabaseConfig {

    @Value("${databaseConnectionPropertiesFile}")
    private String propertiesFile;

    @Bean
    @Profile("test")
    public DataSource testDataSource() {
        final HikariConfig hikariConfig = new HikariConfig(propertiesFile);
        final HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(testDataSource());
    }
}
