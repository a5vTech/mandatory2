package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courseNameInDanish;
    private String courseNameInEnglish;
    private String studyProgram;
    private Boolean mandatory;
    private Integer ects;
    private String courseLanguage;
    private Integer minimumStudents;
    private Integer expectedStudents;
    private Integer maximumStudents;
    private String prerequisites;
    private String learningOutcome;
    private String content;
    private String learningActivities;
    private String examForm;
    private String createdBy;


    private String semester;
    private String classCode;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "course")
    public Set<UserCourse> userCourses = new HashSet<>();


//    @ManyToMany(mappedBy = "courses")
//    private List<User> user = new ArrayList<>();

    //Teachers

    //Students


    public Course() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseNameInDanish() {
        return courseNameInDanish;
    }

    public void setCourseNameInDanish(String courseNameInDanish) {
        this.courseNameInDanish = courseNameInDanish;
    }

    public String getCourseNameInEnglish() {
        return courseNameInEnglish;
    }

    public void setCourseNameInEnglish(String courseNameInEnglish) {
        this.courseNameInEnglish = courseNameInEnglish;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    public String getCourseLanguage() {
        return courseLanguage;
    }

    public void setCourseLanguage(String courseLanguage) {
        this.courseLanguage = courseLanguage;
    }

    public Integer getMinimumStudents() {
        return minimumStudents;
    }

    public void setMinimumStudents(Integer minimumStudents) {
        this.minimumStudents = minimumStudents;
    }

    public Integer getExpectedStudents() {
        return expectedStudents;
    }

    public void setExpectedStudents(Integer expectedStudents) {
        this.expectedStudents = expectedStudents;
    }

    public Integer getMaximumStudents() {
        return maximumStudents;
    }

    public void setMaximumStudents(Integer maximumStudents) {
        this.maximumStudents = maximumStudents;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getLearningOutcome() {
        return learningOutcome;
    }

    public void setLearningOutcome(String learningOutcome) {
        this.learningOutcome = learningOutcome;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLearningActivities() {
        return learningActivities;
    }

    public void setLearningActivities(String learningActivities) {
        this.learningActivities = learningActivities;
    }

    public String getExamForm() {
        return examForm;
    }

    public void setExamForm(String examForm) {
        this.examForm = examForm;
    }


    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

//    public List<User> getUsers() {
//        return user;
//    }
//
//    public void setUsers(List<User> users) {
//        this.user = users;
//    }


    public Set<UserCourse> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(Set<UserCourse> userCourses) {
        this.userCourses = userCourses;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
