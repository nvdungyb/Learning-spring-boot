package com.dzung.springboot.configurationbean;

public class SimpleBean {
    private String userName;

    public SimpleBean(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return "SimpleBean [userName=" + userName + "]";
    }
}
