package com.ICE.Service;

import com.ICE.Entities.Student;

public interface ServiceStudentDao {

    void saveStudent(Student student);


    Student getStudentById(int id);

    Student getStudentByUniversityNo(String universityNo);
}
