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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
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
        List<UserCourse> userCourses = userCourseRepository.findAllUserCoursesByAcceptedOrderBySignUpDate(false);
        List<Course> courses = new ArrayList<>();

        for(UserCourse userCourse : userCourses){
            courses.add(userCourse.getCourse());
        }
        model.addAttribute("courses",courses);
        model.addAttribute("userCourses",userCourses);
        return "admworker/requests";
    }


    @PostMapping(value = "/admworker/requests", params = "reject")
    public String reject(@RequestParam String hiddenId){
        UserCourse userCourse = userCourseRepository.findUserCourse(Long.parseLong(hiddenId));
        userCourseRepository.deleteById(Long.parseLong(hiddenId));
        return "redirect:/admworker/requests";
    }

    @PostMapping(value = "/admworker/requests", params = "accept")
    public String accept(@RequestParam String hiddenId){
        UserCourse userCourse = userCourseRepository.findUserCourse(Long.parseLong(hiddenId));
        userCourse.setAccepted(true);
        userCourseRepository.save(userCourse);
        return "redirect:/admworker/requests";
    }

    @GetMapping("/admworker/allCourses")
    public String allCourses(Model model) {
        List<Course> courses = courseRepository.findAllByOrderByCourseNameInEnglish();
        model.addAttribute("courses", courses);
        return "admworker/all_courses";
    }


    @GetMapping("/settings")
    public String settings(Model model) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentuser);
        model.addAttribute("course", new Course());

        return "settings";
    }

    @SuppressWarnings("Duplicates")
    @GetMapping("/admworker/course/{id}")
    public String course(Model model, @PathVariable Long id) {
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Course course = courseRepository.findCourse(id);
        model.addAttribute("course", course);
        List<User> teachers = CourseController.getCourseTeachers(course);
        List<User> students = CourseController.getCourseStudents(course);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);

        return "admworker/course";
    }











}
