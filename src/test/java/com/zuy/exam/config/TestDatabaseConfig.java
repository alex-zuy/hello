package com.zuy.exam.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class TestDatabaseConfig {

    @Primary
    @Bean
    public DataSource testDataSource() {
        final Properties properties = new Properties();
        properties.setProperty("dataSource.user", "root");
        properties.setProperty("dataSource.password", "password");
        properties.setProperty("dataSource.databaseName", "hello-test");
        properties.setProperty("dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        final HikariConfig hikariConfig = new HikariConfig(properties);
        final HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return  new DataSourceTransactionManager(testDataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(testDataSource());
    }
}
