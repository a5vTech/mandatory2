package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {


    @GetMapping("/student/courses")
    public String courselist(Model model) {

        return "courses";
    }

}
