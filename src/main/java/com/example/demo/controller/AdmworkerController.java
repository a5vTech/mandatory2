package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.model.UserCourse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserCourseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdmworkerController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    @GetMapping("/admworker")
    public String redirectAdmworkerRequests() {

        return "redirect:/admworker/requests";
    }

    @GetMapping("/admworker/requests")
    public String requests(Model model) {
        List<UserCourse> userCourses = userCourseRepository.findAllUserCoursesByAccepted(false);
        List<Course> courses = new ArrayList<>();

        for(UserCourse userCourse : userCourses){
            courses.add(userCourse.getCourse());
        }
        model.addAttribute("courses",courses);
        model.addAttribute("userCourses",userCourses);
        return "admworker/requests";
    }

    @GetMapping("/admworker/studentList")
    public String studentList(Model model) {
        List<User> users = userRepository.findAllByRole("ROLE_STUDENT");
        model.addAttribute("users",users);
        return "admworker/student_list";
    }


    @GetMapping("/admworker/settings")
    public String settings(Model model) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentuser);
        model.addAttribute("course", new Course());

        return "settings";
    }












}
