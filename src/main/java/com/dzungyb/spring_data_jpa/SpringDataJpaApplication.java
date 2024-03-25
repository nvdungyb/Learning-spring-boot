package com.dzungyb.spring_data_jpa;

import com.dzungyb.spring_data_jpa.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringDataJpaApplication.class, args);

        ApplicationContext context = SpringApplication.run(SpringDataJpaApplication.class);
        UserService userService = context.getBean(UserService.class);

        userService.getAllUsers().forEach(System.out::println);

//        User user = new User();
//        user.setHp(101);
//        user.setStamina(100);
//        user.setAtk(100);
//        user.setDef(100);
//        user.setAgi(100);
//
//        userService.saveUser(user);
//
//        System.out.println(userService.getUserById(user.getId()));
    }

}
