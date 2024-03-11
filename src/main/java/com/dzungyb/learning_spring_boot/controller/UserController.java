package com.dzungyb.learning_spring_boot.controller;

import com.dzungyb.learning_spring_boot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> ls = new ArrayList<>();

        User ramesh = new User("ramesh", "ramesh@gmail.com", "ADMIN", "M");
        User admin = new User("admin", "admin@gmail.com", "ADMIN", "M");
        User tampo = new User("tampo", "tampo@gmail.com", "USER", "M");
        User meena = new User("meena", "meena@gmail.com", "USER", "F");

        ls.add(ramesh);
        ls.add(admin);
        ls.add(tampo);
        ls.add(meena);

        model.addAttribute("users", ls);

        return "users";
    }

}
