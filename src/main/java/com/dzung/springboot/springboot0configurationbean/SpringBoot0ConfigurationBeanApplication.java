package com.dzung.springboot.springboot0configurationbean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
public class SpringBoot0ConfigurationBeanApplication {

    @GetMapping("/")
    public void getRequest() {
        System.out.println("Hello Spring Boot");
    }

    public static void main(String[] args) {
        System.out.println("Hello Spring Boot");
        SpringApplication.run(SpringBoot0ConfigurationBeanApplication.class, args);
    }

}
