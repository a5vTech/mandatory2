package com.example.demo.controller;

import com.example.demo.model.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {

    @GetMapping("/course/create")
    public String createCourse(Model model) {
        model.addAttribute("course", new Course());
        return "create_course";
    }
}
