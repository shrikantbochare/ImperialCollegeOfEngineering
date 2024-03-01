package com.ICE.Service;


import com.ICE.DAO.SubjectRegistrationRepository;
import com.ICE.Entities.Faculty;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import com.ICE.Entities.SubjectRegistrationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceSubjectRegistrationDaoImpl implements ServiceSubjectRegistrationDao{

    private SubjectRegistrationRepository subjectRegistrationRepository;


    public ServiceSubjectRegistrationDaoImpl(SubjectRegistrationRepository subjectRegistrationRepository) {
        this.subjectRegistrationRepository = subjectRegistrationRepository;
    }


    @Override
    public void saveSubjectRegistrationRequest(SubjectRegistrationRequest subjectRegistrationRequest) {
        subjectRegistrationRepository.save(subjectRegistrationRequest);
    }


    @Transactional
    @Override
    public void deleteSubjectRegistrationRequest(Student student, Subject subject) {
        subjectRegistrationRepository.deleteByStudentAndSubject(student,subject);
    }


    @Override
    public List<SubjectRegistrationRequest> getRequestsForFaculty(Faculty faculty) {
        return subjectRegistrationRepository.findByFaculty(faculty);
    }


    @Override
    public SubjectRegistrationRequest getRequestOfStudentForSubject(Student student, Subject subject) {
        return subjectRegistrationRepository.findByStudentAndSubject(student,subject);
    }


    @Override
    public List<SubjectRegistrationRequest> getRequestOfStudent(Student student) {
        return subjectRegistrationRepository.findByStudent(student);
    }


    @Override
    public List<SubjectRegistrationRequest> getRequestsOfStudentWithNotRejected(Student student) {
        return subjectRegistrationRepository.findByStudentWithNotRejected(student);
    }
}
