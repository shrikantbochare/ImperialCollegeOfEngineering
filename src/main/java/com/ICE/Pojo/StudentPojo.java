package com.ICE.Pojo;

import com.ICE.Validation.OnCreate;
import com.ICE.Validation.OnUpdate;
import com.ICE.Validation.UniversityId;
import jakarta.validation.constraints.*;

import java.sql.Date;

public class StudentPojo {


    private int id;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @Pattern(regexp = "^[a-zA-Z ]{5,}$", message = "Name can contain small letters, capital letters " +
            "and spaces with minimum size of 5 letters" ,groups = {OnCreate.class, OnUpdate.class})
    private String name;


    @NotNull(groups = {OnCreate.class})
    @Email(message = "Enter valid email address",groups = {OnCreate.class})
    private String email;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @UniversityId(groups = {OnCreate.class})
    private String universityNo;


    @NotNull(groups = {OnCreate.class})
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_])[A-Za-z0-9@_]{5,}$" , message = "Password must contain" +
            " at least one small letter, capital letter, number, and special character like @ or _  " +
            "with minimum size of 5 letters" ,groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @Past(groups = {OnUpdate.class},message = "BirthDate should be in past")
    private Date birthdate;

    @Min(value = 18,groups = {OnUpdate.class},message = "Minimum age must be 18")
    private int age;

    @Pattern(regexp = "^[a-zA-Z0-9, ]+$", message = "Address can contain letters, numbers, spaces and ,",groups = {OnUpdate.class} )
    private String address;


    @Pattern(regexp = "^[a-zA-Z\\s]+$" , message = "City name should contain letters and white spaces if needed" ,groups = {OnUpdate.class})
    private String city;

    @Pattern(regexp = "^[a-zA-Z\\s]+$" , message = "State name should contain letters and white spaces if needed" ,groups = {OnUpdate.class})
    private String state;

    private String department;

    private String course;

    private String semester;


    public StudentPojo() {
    }

    public StudentPojo(String name, String email, String universityNo, String password, String department, String course) {
        this.name = name;
        this.email = email;
        this.universityNo = universityNo;
        this.password = password;
        this.department = department;
        this.course = course;
    }


    public StudentPojo(String name, String email, String universityNo, String password, Date birthdate, String address,
                       String city, String state, String department, String course, String semester,int age) {
        this.name = name;
        this.email = email;
        this.universityNo = universityNo;
        this.password = password;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.department = department;
        this.course = course;
        this.semester = semester;
        this.age=age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversityNo() {
        return universityNo;
    }

    public void setUniversityNo(String universityNo) {
        this.universityNo = universityNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
