package com.ICE.Pojo;

import java.sql.Date;

public class FacultyPojo {

    private int id;


    private String name;

    private String facultyId;

    private String email;

    private String password;

    private String classTeacher;
    private String designation;

    private int age;
    private Date birthdate;

    private String address;

    private String city;

    private String state;

    private String department;


    public FacultyPojo() {
    }


    public FacultyPojo(String name, String facultyId, String email, String password) {
        this.name = name;
        this.facultyId = facultyId;
        this.email = email;
        this.password = password;
    }

    public FacultyPojo(String name, String facultyId, String email, String password, int age, Date birthdate, String address, String city, String state) {
        this.name = name;
        this.facultyId = facultyId;
        this.email = email;
        this.password = password;
        this.age = age;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.state = state;
    }


    public FacultyPojo(String classTeacher, String designation, String department) {
        this.classTeacher = classTeacher;
        this.designation = designation;
        this.department = department;
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

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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
}
