package com.ICE.Entities;


import jakarta.persistence.*;

@Entity
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;


    @Column(unique = true,nullable = false)
    private String email;


    @Column(unique = true,nullable = false)
    private String universityNo;


    @Column(nullable = false)
    private String password;


    private String studyingYear;

    private String semester;


    public Student(String name, String email, String universityNo, String password, String studyingYear, String semester) {
        this.name = name;
        this.email = email;
        this.universityNo = universityNo;
        this.password = password;
        this.studyingYear = studyingYear;
        this.semester = semester;
    }


    public Student() {
    }


    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversityNo() {
        return universityNo;
    }

    public void setUniversityNo(String universityNo) {
        this.universityNo = universityNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudyingYear() {
        return studyingYear;
    }

    public void setStudyingYear(String studyingYear) {
        this.studyingYear = studyingYear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
