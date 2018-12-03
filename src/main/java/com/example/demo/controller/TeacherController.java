package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.model.UserCourse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserCourseRepository;
import com.example.demo.repository.UserRepository;
import net.bytebuddy.description.method.ParameterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
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

    @GetMapping("/teacher/createCourse")
    public String createCourse(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
//        System.out.println(username);
//        model.addAttribute("username", username);
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


    @GetMapping("/teacher/settings")
    public String settings(Model model) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", currentuser);
        model.addAttribute("course", new Course());

        return "settings";
    }

    @PostMapping("/teacher/settings")
    public String settingsPost(@ModelAttribute User user) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        currentuser.setFirstName(user.getFirstName());
        currentuser.setLastName(user.getLastName());
        currentuser.setPhone(user.getPhone());
        currentuser.setEmail(user.getEmail());
        currentuser.setPassword(user.getPassword());
        userRepository.save(currentuser);
        return "redirect:/teacher/settings";
    }

    @GetMapping("/teacher/course/{id}")
    public String course(Model model, @PathVariable Long id) {
//        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Course course = courseRepository.findCourse(id);
        model.addAttribute("course", course);
        List<User> teachers = CourseController.getCourseTeachers(course);
        model.addAttribute("teachers", teachers);


        return "student/course";
    }

    @GetMapping("/teacher/course/edit/{id}")
    public String courseEdit(Model model, @PathVariable Long id) {
//        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Course course = courseRepository.findCourse(id);
        model.addAttribute("course", course);
        List<User> teachers = CourseController.getCourseTeachers(course);
        System.out.println(teachers.size());
        model.addAttribute("teachers", teachers);


        return "teacher/edit_course";
    }


    //TODO: 2x postmappings
    @PostMapping(value = "/teacher/course/edit", params = "update")
    public String courseUpdatePost(@ModelAttribute Course course, @RequestParam(defaultValue = "{{NONE}}") String teachersListString) {
        System.out.println("UDATE BUTTON PRESS 136");

        List<UserCourse> list = userCourseRepository.findAllByCourse(course);
//        for (UserCourse uCourse : list) {
////TODO if list is {{NONE}} delete course ?
//            userCourseRepository.delete(uCourse);
//
//        }
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
        System.out.println("SIZE FROM DB" + courseDB.userCourses.size());
        course.userCourses.clear();
        System.out.println("SIZE AFTER DB" + courseDB.userCourses.size());

        courseDB = course;
//
//        List<User> teachers = new ArrayList<>();
        courseRepository.save(courseDB);
//        System.out.println("SIZE AFTER save to DB"+courseDB.userCourses.size());
//
//        for (String email : teacherMails) {
//            teachers.add(userRepository.findByEmail(email));
//        }

//




    /*    courseDB.setCourseNameInDanish(course.getCourseNameInDanish());
        courseDB.setCourseNameInEnglish(course.getCourseNameInEnglish());
        courseDB.setStudyProgram();
        courseDB.setMandatory();
        courseDB.setEcts();
        courseDB.setCourseLanguage();
        courseDB.setMinimumStudents();
        courseDB.setExpectedStudents();
        courseDB. */

//        model.addAttribute("teachers", teachers);


        return "redirect:/teacher/editCourses";
    }

    @PostMapping(value = "/teacher/course/edit", params = "delete")
    public String courseDeletePost(@ModelAttribute Course course) {
        System.out.println(course.getId());
        courseRepository.deleteById(course.getId());
        //TODO DELETEBYID (DELETES EVERYTHING ) 
        System.out.println("DELETED");
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
