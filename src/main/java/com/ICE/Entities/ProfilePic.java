package com.ICE.Entities;


import jakarta.persistence.*;

@Entity
public class ProfilePic {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String pic;


    @OneToOne(mappedBy = "profilePic")
    private Student student;


    @OneToOne(mappedBy = "profilePic")
    private Faculty faculty;
}
