package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.model.UserCourse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserCourseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("Duplicates")

@Controller
public class TeacherController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCourseRepository userCourseRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/teacher/createCourse")
    public String createCourse(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("course", new Course());

        List<User> teachers = userRepository.findAll();
        model.addAttribute("teachers", teachers);

        return "teacher/create_course";
    }

    @GetMapping("/teacher")
    public String redirectTeacherMyCourses() {

        return "redirect:/teacher/myCourses";
    }


    @PostMapping("/teacher/createCourse")
    public String createCourse(@ModelAttribute Course course, @RequestParam(defaultValue = "") String teachersListString) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        String[] teacherMails = teachersListString.split(",");
        System.out.println("TEACHERS FROM VIEW: " + teacherMails);
        course.setCreatedBy(currentuser.getEmail());

        teacherMails[teacherMails.length-1] = currentuser.getEmail();
        for (String email : teacherMails) {
            User user = userRepository.findByEmail(email);
            UserCourse userCourse = new UserCourse();
            userCourse.setUser(user);
            userCourse.setCourse(course);
            userCourse.setAccepted(true);
            course.getUserCourses().add(userCourse);
            user.userCourses.add(userCourse);
            userRepository.save(user);
        }

        return "redirect:/teacher/myCourses";
    }

    @GetMapping("/teacher/myCourses")
    public String teacherCourses(Model model) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        //TODO get users courses
        List<Course> courses = CourseController.getUserCourses(currentuser);

        model.addAttribute("courses", courses);
        return "teacher/my_courses";
    }


    @PostMapping("/settings")
    public String settingsPost(@ModelAttribute User user) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        currentuser.setFirstName(user.getFirstName());
        currentuser.setLastName(user.getLastName());
        currentuser.setPhone(user.getPhone());
        currentuser.setEmail(user.getEmail());
        System.out.println("DEBUG " + user.getPassword());
        if(!user.getPassword().equalsIgnoreCase("")) {
            currentuser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(currentuser);
        return "redirect:/settings";
    }

    @GetMapping("/teacher/course/{id}")
    public String course(Model model, @PathVariable Long id) {
//        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Course course = courseRepository.findCourse(id);
        model.addAttribute("course", course);
        List<User> teachers = CourseController.getCourseTeachers(course);
        List<User> students = CourseController.getCourseStudents(course);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);

        return "student/course";
    }

    @GetMapping("/teacher/course/edit/{id}")
    public String courseEdit(Model model, @PathVariable Long id) {
//        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Course course = courseRepository.findCourse(id);
        model.addAttribute("course", course);
        List<User> teachers = CourseController.getCourseTeachers(course);
        model.addAttribute("teachers", teachers);

        return "teacher/edit_course";
    }


    @PostMapping(value = "/teacher/course/edit", params = "update")
    public String courseUpdatePost(@ModelAttribute Course course, @RequestParam(defaultValue = "{{NONE}}") String teachersListString) {

        List<UserCourse> list = userCourseRepository.findAllByCourse(course);

        if (!teachersListString.equals("{{NONE}}")) {

            String[] teacherMails = teachersListString.split(",");

            for (String email : teacherMails) {
                User user = userRepository.findByEmail(email);
                UserCourse userCourse = new UserCourse();
                userCourse.setUser(user);
                userCourse.setCourse(course);
                course.getUserCourses().add(userCourse);
                user.userCourses.add(userCourse);
            }

        }
        Course courseDB = courseRepository.findCourse(course.getId());
        Long courseDbId = courseDB.getId();
        course.setId(courseDbId);
        course.userCourses.clear();

        courseDB = course;

        courseRepository.save(courseDB);

        return "redirect:/teacher/editCourses";
    }

    @PostMapping(value = "/teacher/course/edit", params = "delete")
    public String courseDeletePost(@ModelAttribute Course course) {
        courseRepository.deleteById(course.getId());
        return "redirect:/teacher/editCourses";
    }

    @GetMapping("/teacher/editCourses")
    public String editCourses(Model model) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Course> courses = CourseController.getUserCourses(currentuser);
        model.addAttribute("courses", courses);

        return "teacher/edit_Courses";
    }


}
