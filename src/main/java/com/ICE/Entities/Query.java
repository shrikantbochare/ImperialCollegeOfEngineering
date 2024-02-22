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


    public Query() {
    }


    public Query(String title, String query, Date postedDate) {
        this.title = title;
        this.query = query;
        this.postedDate = postedDate;
    }


    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
