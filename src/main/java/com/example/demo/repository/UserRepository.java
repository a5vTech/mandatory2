package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {


    User findByEmail(String email);
    List<User> findAllByRole(String role);
    List<User> findAll();

}
