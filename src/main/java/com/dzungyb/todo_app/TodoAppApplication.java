package com.dzungyb.todo_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.dzungyb.todo_app.controller", "com.dzungyb.todo_app.service", "com.dzungyb.todo_app.repository"})
public class TodoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoAppApplication.class, args);
    }

}

// Đảm bảo là là các component đã được khởi tạo Bean (được scan và tạo Bean) bằng cách sử dụng @ComponentScan.