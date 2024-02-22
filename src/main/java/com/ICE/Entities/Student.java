package com.ICE.Entities;


import jakarta.persistence.*;

import java.sql.Date;
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



    private Date birthdate;

    private String address;

    private String city;

    private String state;


    private String department;

    private String course;

    private String semester;


    @ManyToMany
    private List<Subject> subjects;

    @ManyToOne
    private Faculty faculty;


    @OneToOne
    private ProfilePic profilePic;

    public Student(String name, String email, String universityNo, String password) {
        this.name = name;
        this.email = email;
        this.universityNo = universityNo;
        this.password = password;
    }



    @OneToMany(mappedBy = "student")
    private List<Attendance> attendances;



    @OneToMany(mappedBy = "student")
    private List<Score> scores;



    @OneToMany(mappedBy = "student")
    private List<Query> queries;

    public Student() {
    }


    public int getId() {
        return id;
    }





}
