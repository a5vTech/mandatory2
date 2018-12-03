package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findAll();
    Course findByClassCode(String code);

    @Query(nativeQuery = true, value = "SELECT * FROM course WHERE id = ?1")
    Course findCourse(Long id);

}
