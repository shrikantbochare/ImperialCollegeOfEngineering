package com.ICE.Entities;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    private int age;

    private Date birthdate;

    private String address;

    private String city;

    private String state;

    private String department;

    private String course;

    private String semester;

    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;


    @OneToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    private ProfilePic profilePic;


    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Attendance> attendances;



    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Score> scores;



    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Query> queries;



    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<SubjectRegistrationRequest> subjectRegistrationRequests;

    public Student(String name, String email, String universityNo, String password,String department,String course) {
        this.name = name;
        this.email = email;
        this.universityNo = universityNo;
        this.password = password;
        this.department=department;
        this.course=course;
    }


    public Student(String name, String email, String universityNo, String password, int age, Date birthdate, String address, String city, String state, String department, String course, String semester) {
        this.name = name;
        this.email = email;
        this.universityNo = universityNo;
        this.password = password;
        this.age = age;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.department = department;
        this.course = course;
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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public ProfilePic getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(ProfilePic profilePic) {
        this.profilePic = profilePic;
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

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<SubjectRegistrationRequest> getSubjectRegistrationRequests() {
        return subjectRegistrationRequests;
    }

    public void setSubjectRegistrationRequests(List<SubjectRegistrationRequest> subjectRegistrationRequests) {
        this.subjectRegistrationRequests = subjectRegistrationRequests;
    }

    public void addSubjects(Subject subject)
    {
        if(subjects == null)
        {
            subjects = new ArrayList<>();
        }

        subjects.add(subject);
    }

    public void addAttendance(Attendance attendance)
    {
        if(attendances == null)
        {
            attendances = new ArrayList<>();
        }

        attendances.add(attendance);
    }


    public void addScore(Score score)
    {
        if(scores == null)
        {
            scores = new ArrayList<>();
        }

        scores.add(score);
    }


    public void addRequest(SubjectRegistrationRequest subjectRegistrationRequest)
    {
        if(subjectRegistrationRequests == null)
        {
            subjectRegistrationRequests = new ArrayList<>();
        }

        subjectRegistrationRequests.add(subjectRegistrationRequest);
    }

    public void addQuery(Query query)
    {
        if(queries == null)
        {
            queries = new ArrayList<>();
        }
        queries.add(query);
    }
}
