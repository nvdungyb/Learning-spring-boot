package com.dzung.springboot.configurationbean.database;

import java.sql.Connection;

public abstract class DatabaseConnector {
    private String url, userName, password;

    public abstract Connection connect();

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }
}
