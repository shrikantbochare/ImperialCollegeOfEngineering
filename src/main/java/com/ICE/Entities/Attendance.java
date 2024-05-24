package com.ICE.Entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Attendance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int totalClasses;


    private int presentClasses;


    private float presentPercentage;

    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;


    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;


    public Attendance() {
    }

    public Attendance(int totalClasses, int presentClasses, int presentPercentage,Date updatedDate) {
        this.totalClasses = totalClasses;
        this.presentClasses = presentClasses;
        this.presentPercentage = presentPercentage;
        this.updatedDate=updatedDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public int getPresentClasses() {
        return presentClasses;
    }

    public void setPresentClasses(int presentClasses) {
        this.presentClasses = presentClasses;
    }

    public float getPresentPercentage() {
        return presentPercentage;
    }

    public void setPresentPercentage(float presentPercentage) {
        this.presentPercentage = presentPercentage;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}


