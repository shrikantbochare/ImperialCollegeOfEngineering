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





//===============> Save or update student start ===============>
    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }
//<============== Save or update student end <===============






//===============> Get the student by Id start ===============>
    @Override
    public Student getStudentById(int id) {
        Optional<Student> value = studentRepository.findById(id);
        Student student = value.orElseGet(Student :: new);
        return student;
    }
//<============== Get the student by Id end <===============





//===============> Get the student by universityNo start ===============>
    @Override
    public Student getStudentByUniversityNo(String universityNo) {
        return studentRepository.findByUniversityNo(universityNo);
    }
//<============== Get the student by universityNo end <===============
}
