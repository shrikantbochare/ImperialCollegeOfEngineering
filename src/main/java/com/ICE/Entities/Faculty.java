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

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    @OneToMany(mappedBy = "faculty")
    private List<Subject> subjects;


    @OneToMany(mappedBy = "faculty")
    private List<Query> queries;


    @OneToOne
    private ProfilePic profilePic;
}
