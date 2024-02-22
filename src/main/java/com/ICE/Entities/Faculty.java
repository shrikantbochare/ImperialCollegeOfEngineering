package com.ICE.Entities;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Faculty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    private String facultyId;

    private String email;

    private String password;


    private Date birthdate;

    private String address;

    private String city;

    private String state;

    private String department;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    @OneToMany(mappedBy = "faculty")
    private List<Subject> subjects;


    @OneToMany(mappedBy = "faculty")
    private List<Query> queries;


    @OneToOne
    private ProfilePic profilePic;


    public Faculty(String name, String facultyId, String email, String password) {
        this.name = name;
        this.facultyId = facultyId;
        this.email = email;
        this.password = password;
    }

    public Faculty() {
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

    public void setPassword(String password) {
        this.password = password;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

    public ProfilePic getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(ProfilePic profilePic) {
        this.profilePic = profilePic;
    }
}
