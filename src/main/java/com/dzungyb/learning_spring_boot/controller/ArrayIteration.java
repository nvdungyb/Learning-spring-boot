package com.dzungyb.learning_spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArrayIteration {
    @GetMapping("/array")
    public String arrayIteration(Model model){
        String[] planets = {"MERCURY", "VENUS", "EARTH", "MARS", "JUPITER", "SATURN", "URANUS", "NEPTUNE"};
        model.addAttribute("arrays", planets);

        return "arrayDemo";
    }

}
