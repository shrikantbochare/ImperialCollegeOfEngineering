package com.ICE.Pojo;

import com.ICE.Validation.OnCreate;
import com.ICE.Validation.OnUpdate;
import jakarta.validation.constraints.*;

import java.sql.Date;

public class FacultyPojo {

    private int id;

    @Pattern(regexp = "^[a-zA-Z ]{5,}$", message = "Name can contain small letters, capital letters and spaces" +
            " with minimum size of 5 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Pattern(regexp = "^(FIT|FCS|FCE|FME|FEE|FET)\\d{6}$" , message = "Faculty Id must start with FIT or FCS or FCE or FME or FEE or FET " +
            "followed by 6 digit number",  groups = {OnCreate.class})
    private String facultyId;

    @NotNull( groups = {OnCreate.class})
    @Email(message = "Enter valid email address", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_])[A-Za-z0-9@_]{5,}$" ,message = "Password must contain at least one small letter" +
            ", one capital letter, one number and special character like @ or _  with minimum length of 5 characters",  groups = {OnCreate.class})
    private String password;

    private String classTeacher;
    private String designation;

    @Min(value = 25 ,  groups = { OnUpdate.class},message = "Minimum age must be 25")
    private int age;

    @Past(groups = {OnUpdate.class} , message = "Birthdate should be in past")
    private Date birthdate;

    @Pattern(regexp = "^[a-zA-Z0-9, ]+$", message = "Address can contain letters, numbers, spaces and ,",groups = {OnUpdate.class} )
    private String address;

    @Pattern(regexp = "^[a-zA-Z\\s]+$" , message = "City name should contain letters and white spaces if needed" ,groups = {OnUpdate.class})
    private String city;

    @Pattern(regexp = "^[a-zA-Z\\s]+$" , message = "State name should contain letters and white spaces if needed" ,groups = {OnUpdate.class})
    private String state;

    private String department;


    public FacultyPojo() {
    }


    public FacultyPojo(String name, String facultyId, String email, String password) {
        this.name = name;
        this.facultyId = facultyId;
        this.email = email;
        this.password = password;
    }

    public FacultyPojo(String name, String facultyId, String email, String password, int age, Date birthdate, String address, String city, String state) {
        this.name = name;
        this.facultyId = facultyId;
        this.email = email;
        this.password = password;
        this.age = age;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.state = state;
    }


    public FacultyPojo(String classTeacher, String designation, String department) {
        this.classTeacher = classTeacher;
        this.designation = designation;
        this.department = department;
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

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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
}
