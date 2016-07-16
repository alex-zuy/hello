package com.zuy.exam.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        final Properties properties = new Properties();
        properties.setProperty("dataSource.user", "root");
        properties.setProperty("dataSource.password", "password");
        properties.setProperty("dataSource.databaseName", "hello");
        properties.setProperty("dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        final HikariConfig hikariConfig = new HikariConfig(properties);
        final HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return  new DataSourceTransactionManager(dataSource());
    }
}
