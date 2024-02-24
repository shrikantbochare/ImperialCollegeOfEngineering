package com.ICE.Entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Subject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String subId;

    private String department;

    private String course;

    private String semester;

    private int credits;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;


    @ManyToOne
    private Faculty faculty;


    @OneToMany(mappedBy = "subject")
    private List<Attendance> attendances;



    @OneToMany(mappedBy = "subject")
    private List<Score> scores;


    public Subject(String name, String subId, String department, String course, String semester, int credits) {
        this.name = name;
        this.subId = subId;
        this.department = department;
        this.course = course;
        this.semester = semester;
        this.credits = credits;
    }

    public Subject() {
    }

    public int getId() {
        return id;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
