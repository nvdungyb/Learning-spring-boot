package com.dzung.springboot.configurationbean;

import com.dzung.springboot.configurationbean.database.DatabaseConnector;
import com.dzung.springboot.configurationbean.database.MysqlConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.username}")
    String userName;

    @Value("${spring.datasource.url}")
    String url;

    @Bean
    DatabaseConnector getConnection() {
        DatabaseConnector mysqlConnector = new MysqlConnector();
        mysqlConnector.setUrl(url);
        mysqlConnector.setUserName(userName);
        mysqlConnector.setPassword(password);

        return mysqlConnector;
    }
}
