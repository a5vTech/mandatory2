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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserCourseRepository userCourseRepository;

    //Used to redirect from kea logo
    @GetMapping("/student")
    public String redirectTeacherMyCourses() {

        return "redirect:/student/myCourses";
    }

    @GetMapping("/student/course")
    public String student(Model model) {
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        return "student/course";
    }


    @GetMapping("/student/course/{id}")
    public String course(Model model, @PathVariable Long id) {
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Course course = courseRepository.findCourse(id);
        model.addAttribute("course", course);
        List<User> teachers = CourseController.getCourseTeachers(course);
        List<User> students = CourseController.getCourseStudents(course);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);

        return "student/course";
    }

    @PostMapping(value = "/teacher/course", params = "signUp")
    public String signUpCourse(@ModelAttribute Course course) {
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        UserCourse testUserCourse = userCourseRepository.findAllByUserIdAndCourseId(currentUser.getId(), course.getId());
        if (testUserCourse == null) {

            UserCourse userCourse = new UserCourse();
            userCourse.setCourse(course);
            userCourse.setUser(currentUser);
            userCourse.setSignUpDate(LocalDateTime.now());

            currentUser.userCourses.add(userCourse);
            course.userCourses.add(userCourse);
            try {
                userCourseRepository.save(userCourse);
            } catch (Exception e) {
                return "redirect:/student/course/" + course.getId() + "?error";

            }
        }
        return "redirect:/student/myCourses";
    }


    @GetMapping("/student/myCourses")
    public String myCourses(Model model) {
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        List<UserCourse> userCourses = userCourseRepository.findAllUserCoursesByUserIdAndAccepted(currentUser.getId(), true);
        List<Course> courses = new ArrayList<>();
        for (UserCourse userCourse : userCourses) {
            courses.add(userCourse.getCourse());
        }
        model.addAttribute("courses", courses);


        return "student/my_courses";
    }

    @GetMapping("/student/findCourses")
    public String findCourses(Model model) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("courses", courseRepository.findAllByOrderByCourseNameInEnglish());

        List<UserCourse> userCourses = userCourseRepository.findAllUserCoursesByUserIdAndAccepted(currentuser.getId(), false);

        return "student/find_courses";

    }

    @GetMapping("/student/pendingCourses")
    public String pendingCourses(Model model) {
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        List<UserCourse> userCourses = userCourseRepository.findAllUserCoursesByUserIdAndAcceptedOrderBySignUpDateDesc(currentUser.getId(), false);
        List<Course> courses = new ArrayList<>();
        for (UserCourse userCourse : userCourses) {
            courses.add(userCourse.getCourse());
        }
        model.addAttribute("courses", courses);
        model.addAttribute("userCourses", userCourses);

        return "student/pending_courses";
    }

}
