package com.example.demo.controller;


import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
//@RequestMapping(path = "/logins")
public class LoginController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        User user = userRepository.findByEmail("jesp688a@stud.kea.dk");
        Course cour = courseRepository.findByClassCode("DAT20C");
        for (User courUser : cour.getUsers()) {
            System.out.println("User: " + courUser.getEmail());
        }

        for (Course c : user.getCourses()) {
            System.out.println("Class name " + c.getClassCode());
        }


        return "test";
    }

    public void createTestUser() {

        User user = userRepository.findByEmail("jesp688a@stud.kea.dk");
//        User user = new User();
//        user.setFirstName("Mikkel");
//        user.setEmail("1mikk@stud.kea.dk");
//        user.setRole("ROLE_STUDENT");
//        user.setPassword(passwordEncoder.encode("1234"));
        Course c = new Course();
        c.setClassCode("DAT21C");

        user.getCourses().add(c);

        userRepository.save(user);

    }

    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }
}
