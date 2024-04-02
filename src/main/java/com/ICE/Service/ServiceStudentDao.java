package com.ICE.Service;

import com.ICE.Entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceStudentDao {

    void saveStudent(Student student);


    Student getStudentById(int id);

    Object getStudentByUniversityNo(String universityNo);

//    Page<Student> getAllStudentsOfSubject(int id, Pageable pageable);
}
