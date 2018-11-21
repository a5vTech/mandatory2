package com.example.demo.controller;


import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/api/courses")
    public ResponseEntity<List<Course>> courses() {
        List<Course> courses = courseRepository.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


    @GetMapping("/api/student/courses/")
    public ResponseEntity<List> studentCourses(@RequestParam String email) {
        List<Course> courses = userRepository.findByEmail(email).getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


}
