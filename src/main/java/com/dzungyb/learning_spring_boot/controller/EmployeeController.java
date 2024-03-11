package com.dzungyb.learning_spring_boot.controller;

import com.dzungyb.learning_spring_boot.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/index")
    public String usingThymeleaf(Model model) {
        List<Employee> ls = new ArrayList<>();
        ls.add(new Employee("Ramesh", "Fadatare", "ramesh@gmail.com"));
        ls.add(new Employee("John", "Cena", "john@gmail.com"));
        ls.add(new Employee("Tom", "Cruise", "tom@gmail.com"));
        ls.add(new Employee("Tony", "Stark", "tony@gmail.com"));
        model.addAttribute("employees", ls);
        return "index";
    }

}
