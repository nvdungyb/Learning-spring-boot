package com.dzung.springboot.configurationbean.database;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnector extends DatabaseConnector {
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Override
    public Connection connect() {
        try {
            System.out.println(this.getUrl());
            Class.forName(driver);
            Connection connect = DriverManager.getConnection(this.getUrl(), this.getUserName(), this.getPassword());
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
