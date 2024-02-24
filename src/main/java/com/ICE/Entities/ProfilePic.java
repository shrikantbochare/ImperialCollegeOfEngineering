package com.ICE.Entities;


import jakarta.persistence.*;

@Entity
public class ProfilePic {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String pic;


    @OneToOne(mappedBy = "profilePic")
    private Student student;


    @OneToOne(mappedBy = "profilePic")
    private Faculty faculty;


    public ProfilePic(String pic) {
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public ProfilePic() {
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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


    @Override
    public String toString() {
        return "ProfilePic{" +
                "id=" + id +
                ", pic='" + pic + '\'' +
                '}';
    }
}
