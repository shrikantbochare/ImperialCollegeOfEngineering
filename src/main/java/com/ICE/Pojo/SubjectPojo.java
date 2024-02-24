package com.ICE.Pojo;

public class SubjectPojo {

    private int id;


    private String name;

    private String subId;

    private String department;

    private String course;

    private String semester;

    private int credits;


    public SubjectPojo(String name, String subId, String department, String course, String semester, int credits) {
        this.name = name;
        this.subId = subId;
        this.department = department;
        this.course = course;
        this.semester = semester;
        this.credits = credits;
    }


    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public SubjectPojo() {
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
