package com.ICE.Pojo;

public class AttendancePojo {


    private int id;

    private int totalClasses;

    private int presentClasses;

    private int presentPercentage;


    public AttendancePojo() {
    }

    public AttendancePojo(int totalClasses, int presentClasses, int presentPercentage) {
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
}
