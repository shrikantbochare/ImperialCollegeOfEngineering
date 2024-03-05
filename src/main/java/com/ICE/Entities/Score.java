package com.ICE.Entities;

import jakarta.persistence.*;

import java.sql.Date;


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


    public Score(int ct1, int ct2, int internal, int endSem, String grade) {
        this.ct1 = ct1;
        this.ct2 = ct2;
        this.internal = internal;
        this.endSem = endSem;
        this.grade = grade;
    }


    public Score() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Date getExamDate() {
//        return examDate;
//    }
//
//    public void setExamDate(Date examDate) {
//        this.examDate = examDate;
//    }

    public int getCt1() {
        return ct1;
    }

    public void setCt1(int ct1) {
        this.ct1 = ct1;
    }

    public int getCt2() {
        return ct2;
    }

    public void setCt2(int ct2) {
        this.ct2 = ct2;
    }

    public int getInternal() {
        return internal;
    }

    public void setInternal(int internal) {
        this.internal = internal;
    }

    public int getEndSem() {
        return endSem;
    }

    public void setEndSem(int endSem) {
        this.endSem = endSem;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", ct1=" + ct1 +
                ", ct2=" + ct2 +
                ", internal=" + internal +
                ", endSem=" + endSem +
                ", grade='" + grade + '\'' +
                ", subject=" + subject.getId() +
                ", student=" + student.getId() +
                '}';
    }
}
