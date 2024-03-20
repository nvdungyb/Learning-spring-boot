package com.dzung.springboot.configurationbean.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector extends DatabaseConnector {

    @Override
    public Connection connect() {
        try {
            System.out.println(this.getUrl());
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection(this.getUrl(), this.getUserName(), this.getPassword());
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
