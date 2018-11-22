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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SessionAttributes("username")
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
//        System.out.println(username);
//        model.addAttribute("username", username);
        model.addAttribute("course", new Course());


        List<User> teachers = userRepository.findAllByRole("ROLE_TEACHER");
        model.addAttribute("teachers", teachers);

        return "teacher/create_course";
    }


    @GetMapping("/teacher/myCourses")
    public String teacherCourses(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        List<Course> courses = userRepository.findByEmail(username).getCourses();
        model.addAttribute("courses", courses);
        return "teacher/my_courses";
    }

    @PostMapping("/teacher/createCourse")
    public String createCourse(@ModelAttribute Course course, @RequestParam String teachersListString) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());


        String[] teacherMails = teachersListString.split(",");

        for (String email : teacherMails) {
            User user = userRepository.findByEmail(email);
            course.getUsers().add(user);
            user.getCourses().add(course);
        }
        course.getUsers().add(currentuser);
        currentuser.getCourses().add(course);


        System.out.println(course.getCourselanguage());
        System.out.println(course.getMandatory());
        System.out.println(teachersListString);
        System.out.println(course.getUsers().size());

        courseRepository.save(course);


        return "redirect:/teacher/myCourses";
    }


    @GetMapping("/teacher/settings")
    public String settings(Model model) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentuser);
        model.addAttribute("course", new Course());

        return "settings";
    }
}
