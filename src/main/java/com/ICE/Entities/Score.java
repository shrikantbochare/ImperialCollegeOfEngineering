package com.ICE.Entities;

import jakarta.persistence.*;


@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int ct1;

    private int ct2;

    private int internal;


    private int endSem;


    private String grade;


    @ManyToOne
    private Subject subject;


    @ManyToOne
    private Student student;
}
