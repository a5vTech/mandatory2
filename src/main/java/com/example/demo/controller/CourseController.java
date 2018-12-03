package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.model.UserCourse;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {


    public static List<Course> getUserCourses(User user) {
        List<Course> courses = new ArrayList<>();
        for (UserCourse userCourse : user.getUserCourses()) {
            courses.add(userCourse.getCourse());
        }
        return courses;
    }

    public static List<User> getCourseTeachers(Course course) {
        List<User> teachers = new ArrayList<>();

        for (UserCourse userCourse : course.getUserCourses()) {
            if (userCourse.getUser().getRole().equals("ROLE_TEACHER")) {
                teachers.add(userCourse.getUser());
            }
        }
        return teachers;
    }


}