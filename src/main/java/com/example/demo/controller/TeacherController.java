package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {

    @GetMapping("/course/create")
    public String createCourse(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String username = auth.getName();
        System.out.println(username);
        model.addAttribute("user",username);
        model.addAttribute("course", new Course());
        return "create_course";
    }

}
