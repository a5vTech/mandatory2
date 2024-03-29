package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserCourseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@SessionAttributes("username")
@Controller

public class LoginController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserCourseRepository userCourseRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping({"/", "/login"})
    public String login(Model model) {
//        createTestUser();
        return "login";
    }



    public void createTestUser() {
        User user = new User();
        user.setFirstName("Mikkel");
        user.setLastName("Kofoed");
        user.setEmail("mikk065y@stud.kea.dk");
        user.setRole("ROLE_STUDENT");
        user.setPassword(passwordEncoder.encode("1234"));


        userRepository.save(user);

    }

    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }
}
