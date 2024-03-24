package com.ICE.Entities;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
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


    private String designation;

    private String classTeacher;

    private int age;

    private Date birthdate;

    private String address;

    private String city;

    private String state;

    private String department;

    private List<String> roles;

    @OneToMany(mappedBy = "faculty",fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "faculty",fetch = FetchType.LAZY)
    private List<Subject> subjects;


    @OneToMany(mappedBy = "faculty",fetch = FetchType.LAZY)
    private List<Query> queries;


    @OneToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
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


    public void addSubjects(Subject subject)
    {
        if(subjects == null)
        {
            subjects =new ArrayList<>();
        }
        subjects.add(subject);
    }


    public void addStudent(Student student)
    {
        if(students == null)
        {
            students = new ArrayList<>();
        }

        students.add(student);
    }


    public void addQuery(Query query)
    {
        if(queries == null)
        {
            queries = new ArrayList<>();
        }
        queries.add(query);
    }

    public void addRole(String role)
    {
        if(roles == null)
        {
            roles = new ArrayList<>();
        }
        roles.add(role);
    }

    public List<String> getRoles() {
        return roles;
    }
}
