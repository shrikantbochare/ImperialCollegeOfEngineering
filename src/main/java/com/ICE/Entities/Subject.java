package com.ICE.Entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Subject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;


    private String department;

    private String course;

    private String semester;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;


    @ManyToOne
    private Faculty faculty;


    @OneToMany(mappedBy = "subject")
    private List<Attendance> attendances;



    @OneToMany(mappedBy = "subject")
    private List<Score> scores;




}
