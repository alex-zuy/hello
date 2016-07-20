package com.zuy.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private static final String DATA_SOURCE_RESOURCE_NAME = "java:comp/env/jdbc/helloDS";

    @Profile("default")
    @Bean(destroyMethod="")
    public DataSource dataSource() throws NamingException {
        final InitialContext context = new InitialContext();
        return (DataSource) context.lookup(DATA_SOURCE_RESOURCE_NAME);
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return  new DataSourceTransactionManager(dataSource);
    }
}
