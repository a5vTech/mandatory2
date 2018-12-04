package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime signUpDate;

    private Boolean accepted = false;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Course course;


    public UserCourse() {
    }


    public UserCourse(LocalDateTime signUpDate, User user, Course course) {
        this.signUpDate = signUpDate;
        this.user = user;
        this.course = course;
    }


    public UserCourse(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDateTime signUpDate) {
        this.signUpDate = signUpDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
