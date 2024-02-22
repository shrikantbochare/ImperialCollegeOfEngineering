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


    public Attendance() {
    }

    public Attendance(int totalClasses, int presentClasses, int presentPercentage) {
        this.totalClasses = totalClasses;
        this.presentClasses = presentClasses;
        this.presentPercentage = presentPercentage;
    }


    public int getId() {
        return id;
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

    public int getPresentPercentage() {
        return presentPercentage;
    }

    public void setPresentPercentage(int presentPercentage) {
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


