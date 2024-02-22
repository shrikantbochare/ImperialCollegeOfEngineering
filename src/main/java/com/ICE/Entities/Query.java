package com.ICE.Entities;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Query {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String title;


    @Column(length = 500)
    private String query;


    private Date postedDate;


    @Column(length = 500)
    private String remark;

    @ManyToOne
    private Student student;


    @ManyToOne
    private Faculty faculty;
}
