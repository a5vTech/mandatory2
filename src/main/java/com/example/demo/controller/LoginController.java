package com.example.demo.controller;


import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

@SessionAttributes("username")
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
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        User user = userRepository.findByEmail("jesp688a@stud.kea.dk");
//        Course cour = courseRepository.findByClassCode("DAT20C");
//        for (User courUser : cour.getUsers()) {
//            System.out.println("User: " + courUser.getEmail());
//        }
//
//        for (Course c : user.getCourses()) {
//            System.out.println("Class name " + c.getClassCode());
//        }
//        File file = new File("C:\\Users\\mikke_000\\Desktop\\clock.png");
//        byte[] bFile = new byte[(int) file.length()];
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            fileInputStream.read(bFile);
//            fileInputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        user.setImage(bFile);
//        userRepository.save(user);
//
//
//        try {
//            FileOutputStream fos = new FileOutputStream("C:\\Users\\mikke_000\\floobits\\share\\a5vTech\\Mandatory2\\src\\main\\resources\\static\\Images\\user.png");
//            fos.write(user.getImage());
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        return "test";
    }

    public void createTestUser() {

//        User user = userRepository.findByEmail("mikk@stud.kea.dk");
        User user = new User();
        user.setFirstName("Constantin");
        user.setLastName("Alexandru Gheorghiasa");
        user.setEmail("coag@kea.dk");
        user.setRole("ROLE_TEACHER");
        user.setPassword(passwordEncoder.encode("1234"));
//        Course c = new Course();
//        c.setClassCode("DAT21C");
//
//        user.getCourses().add(c);

        userRepository.save(user);

    }

    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }
}
