package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/student/courses")
    public String courselist(Model model) {

        return "courses";
    }


    @GetMapping("/student/home")
    public String student(Model model) {
        //Get current user
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());


        return "student/home";
    }

}
