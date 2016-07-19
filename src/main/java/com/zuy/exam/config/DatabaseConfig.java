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

    @Profile("default")
    @Bean(destroyMethod="")
    public DataSource dataSource() throws NamingException {
        final InitialContext context = new InitialContext();
        return (DataSource) context.lookup("java:comp/env/jdbc/helloDS");
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return  new DataSourceTransactionManager(dataSource);
    }
}
