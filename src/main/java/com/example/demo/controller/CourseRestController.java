package com.example.demo.controller;


import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.model.UserCourse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserCourseRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseRestController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/api/courses")
    public ResponseEntity<List<Course>> courses() {
        List<Course> courses = courseRepository.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


//    @GetMapping("/api/student/courses/")
//    public ResponseEntity<List> studentCourses(@RequestParam String email) {
//        List<Course> courses = userRepository.findByEmail(email).getCourses();
//        return new ResponseEntity<>(courses, HttpStatus.OK);
//    }


    @GetMapping("/testPassword")
    public ResponseEntity<String> passCheck(@RequestParam String oldPass) {
        User user = userRepository.findByEmail("s");
        if (passwordEncoder.matches(oldPass, user.getPassword())) {

            return new ResponseEntity<>("MATCH", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NO MATCH", HttpStatus.OK);

        }

    }


    @GetMapping("/admworker/requestinfo")
    public ResponseEntity<UserCourse> requestInfo(@RequestParam String id) {
       UserCourse userCourse = userCourseRepository.findUserCourse(Long.parseLong(id));
        return new ResponseEntity<>(userCourse, HttpStatus.OK);
    }


}
