package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;

//Used to redirect from kea logo
    @GetMapping("/student")
    public String redirectTeacherMyCourses() {

        return "redirect:/student/myCourses";
    }

    @GetMapping("/student/course")
    public String student(Model model) {
        //Get current user
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());


        return "student/course";
    }


    @GetMapping("/student/course/{id}")
    public String course(Model model, @PathVariable Long id) {
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        for (Course course : currentUser.getCourses()) {
            if (course.getId() == id) {
                model.addAttribute("course", course);
            }
        }

        return "student/course";
    }

    @GetMapping("/student/myCourses")
    public String myCourses(Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        //Add current users email to the model
        model.addAttribute("username", currentUserEmail);

        //Load students courses
        List<Course> courses = userRepository.findByEmail(currentUserEmail).getCourses();
        model.addAttribute("courses", courses);
        return "student/my_courses";
    }

    @GetMapping("/student/findCourses")
    public String findCourses(Model model) {

        return "student/find_courses";

    }

    @GetMapping("/student/pendingCourses")
    public String pendingCourses(Model model) {

        return "student/pending_courses";
    }

    @GetMapping("/student/settings")
    public String settings(Model model) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentuser);
        model.addAttribute("course", new Course());

        return "settings";
    }

}
