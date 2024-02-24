package com.ICE.Pojo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

public class StudentPojo {


    private int id;

    private String name;

    private String email;

    private String universityNo;

    private String password;

    private Date birthdate;

    private int age;

    private String address;

    private String city;

    private String state;

    private String department;

    private String course;

    private String semester;


    public StudentPojo() {
    }

    public StudentPojo(String name, String email, String universityNo, String password, String department, String course) {
        this.name = name;
        this.email = email;
        this.universityNo = universityNo;
        this.password = password;
        this.department = department;
        this.course = course;
    }


    public StudentPojo(String name, String email, String universityNo, String password, Date birthdate, String address,
                       String city, String state, String department, String course, String semester,int age) {
        this.name = name;
        this.email = email;
        this.universityNo = universityNo;
        this.password = password;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.department = department;
        this.course = course;
        this.semester = semester;
        this.age=age;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
}
