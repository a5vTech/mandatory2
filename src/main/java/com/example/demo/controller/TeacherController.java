package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/teacher/createCourse")
    public String createCourse(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        model.addAttribute("user", username);
        model.addAttribute("course", new Course());
        return "teacher/create_course";
    }

    @GetMapping("/teacher/myCourses")
    public String teacherCourses(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username",username);
        List<Course> courses = userRepository.findByEmail(username).getCourses();
        model.addAttribute("courses",courses);

        return "teacher/my_courses";
    }

    @PostMapping("/teacher/createCourse")
    public String createCourse(@ModelAttribute Course course, @RequestParam String user) {
        System.out.println(user);
        User currentuser = userRepository.findByEmail(user);
        System.out.println(currentuser.getFirstName());
        currentuser.getCourses().add(course);
        userRepository.save(currentuser);


        return "redirect:/teacher/myCourses";
    }
}
