package com.example.demo.repository;

import com.example.demo.model.UserCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends CrudRepository<UserCourse, Long> {

}
