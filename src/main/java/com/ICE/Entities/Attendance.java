package com.ICE.Entities;

import jakarta.persistence.*;

@Entity
public class Attendance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int totalClasses;


    private int presentClasses;


    private int presentPercentage;

    @ManyToOne
    private Subject subject;


    @ManyToOne
    private Student student;
}
