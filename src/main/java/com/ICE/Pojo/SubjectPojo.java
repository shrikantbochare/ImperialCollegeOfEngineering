package com.ICE.Pojo;

public class SubjectPojo {

    private int id;


    private String name;


    private String department;

    private String course;

    private String semester;


    public SubjectPojo(String name, String department, String course, String semester) {
        this.name = name;
        this.department = department;
        this.course = course;
        this.semester = semester;
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
