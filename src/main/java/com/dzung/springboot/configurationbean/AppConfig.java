package com.dzung.springboot.configurationbean;

import com.dzung.springboot.configurationbean.database.DatabaseConnector;
import com.dzung.springboot.configurationbean.database.MysqlConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    DatabaseConnector getConnection() {
        DatabaseConnector mysqlConnector = new MysqlConnector();
        mysqlConnector.setUrl("jdbc:mysql://localhost:3306/learning");
        mysqlConnector.setUserName("root");
        mysqlConnector.setPassword("Dung3032003_135709");

        return mysqlConnector;
    }
}
