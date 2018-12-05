package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.UserCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends CrudRepository<UserCourse, Long> {


    List<UserCourse> findAllByCourse(Course course);
    void deleteAllByCourseId(Long id);
    List<UserCourse> findAllUserCoursesByUserIdAndAccepted(Long id,Boolean accepted);
    List<UserCourse> findAllUserCoursesByUserIdAndAcceptedOrderBySignUpDateDesc(Long id,Boolean accepted);
    List<UserCourse> findAllUserCoursesByAccepted(Boolean accepted);
    List<UserCourse> findAllUserCoursesByAcceptedOrderBySignUpDate(Boolean accepted);
    UserCourse findAllByUserIdAndCourseId(Long userId, Long courseId);
    @Query(value = "SELECT * FROM user_course WHERE id = ?1", nativeQuery = true)
    UserCourse findUserCourse(Long id);


}
