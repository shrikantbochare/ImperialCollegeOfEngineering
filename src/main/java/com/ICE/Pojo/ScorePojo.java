package com.ICE.Pojo;

public class ScorePojo {

    private int id;

    private int ct1;

    private int ct2;

    private int internal;

    private int endSem;

    private String grade;


    public ScorePojo(int ct1, int ct2, int internal, int endSem, String grade) {
        this.ct1 = ct1;
        this.ct2 = ct2;
        this.internal = internal;
        this.endSem = endSem;
        this.grade = grade;
    }


    public ScorePojo() {
    }


    public int getId() {
        return id;
    }

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
}
