package com.ICE.Entities;


import jakarta.persistence.*;

@Entity
public class SubjectRegistrationRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;


    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;


    private String status;


    public SubjectRegistrationRequest(Student student, Subject subject, String status) {
        this.student = student;
        this.subject = subject;
        this.status = status;
    }

    public SubjectRegistrationRequest() {
    }


    public int getId() {
        return id;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
