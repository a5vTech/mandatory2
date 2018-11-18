package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String courseNameInDanish;
    private String courseNameInEnglish;
    private String studyProgram;
    private Boolean mandatory;
    private Integer Ects;
    private String courselanguage;
    private Integer minimumStudents;
    private Integer expectedStudents;
    private Integer maximumStudents;
    private String prerequisites;
    private String learningOutcome;
    private String content;
    private String learningActivities;
    private String examForm;


    private String semester;
    private String classCode;
    @ManyToMany(mappedBy = "courses")
    private List<User> user = new ArrayList<>();

    //Teachers

    //Students


    public Course() {
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
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
        return Ects;
    }

    public void setEcts(Integer ects) {
        Ects = ects;
    }

    public String getCourselanguage() {
        return courselanguage;
    }

    public void setCourselanguage(String courselanguage) {
        this.courselanguage = courselanguage;
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

    public List<User> getUsers() {
        return user;
    }

    public void setUsers(List<User> users) {
        this.user = users;
    }
}
