package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.UserCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends CrudRepository<UserCourse, Long> {


    List<UserCourse> findAllByCourse(Course course);
     void deleteAllByCourseId(Long id);
}
