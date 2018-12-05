package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.model.UserCourse;
import org.springframework.stereotype.Controller;

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

    public static List<User> getCourseStudents(Course course) {
        List<User> students = new ArrayList<>();

        for (UserCourse userCourse : course.getUserCourses()) {
            if (userCourse.getUser().getRole().equals("ROLE_STUDENT") && userCourse.getAccepted()) {
                students.add(userCourse.getUser());
            }
        }
        return students;
    }


}