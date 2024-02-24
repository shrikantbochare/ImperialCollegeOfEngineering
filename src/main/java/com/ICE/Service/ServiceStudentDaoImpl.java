package com.ICE.Service;


import com.ICE.DAO.StudentRepository;
import com.ICE.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceStudentDaoImpl implements ServiceStudentDao{


    private StudentRepository studentRepository;


    @Autowired
    public ServiceStudentDaoImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }


    @Override
    public Student getStudentById(int id) {
        Optional<Student> value = studentRepository.findById(id);
        Student student = value.orElseGet(Student :: new);
        return student;
    }
}
