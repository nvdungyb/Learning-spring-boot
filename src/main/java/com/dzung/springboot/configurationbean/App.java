package com.dzung.springboot.configurationbean;

import com.dzung.springboot.configurationbean.database.MysqlConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

        MysqlConnector mysqlConnector = context.getBean(MysqlConnector.class);
        System.out.println(mysqlConnector.connect());
    }
}
