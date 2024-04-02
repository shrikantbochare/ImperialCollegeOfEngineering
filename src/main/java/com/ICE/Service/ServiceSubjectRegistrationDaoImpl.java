package com.ICE.Service;


import com.ICE.DAO.SubjectRegistrationRepository;
import com.ICE.Entities.Faculty;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import com.ICE.Entities.SubjectRegistrationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceSubjectRegistrationDaoImpl implements ServiceSubjectRegistrationDao{

    private SubjectRegistrationRepository subjectRegistrationRepository;


    public ServiceSubjectRegistrationDaoImpl(SubjectRegistrationRepository subjectRegistrationRepository) {
        this.subjectRegistrationRepository = subjectRegistrationRepository;
    }





//===============> Create subject registration request start ===============>
    @Override
    public void saveSubjectRegistrationRequest(SubjectRegistrationRequest subjectRegistrationRequest) {
        subjectRegistrationRepository.save(subjectRegistrationRequest);
    }
//<============== Create subject registration request end <===============





//===============> Delete subject registration request start ===============>
    @Transactional
    @Override
    public void deleteSubjectRegistrationRequest(Student student, Subject subject) {
        subjectRegistrationRepository.deleteByStudentAndSubject(student,subject);
    }
//<============== Delete subject registration request end <===============





//===============> Get requests for a faculty start ===============>
    @Override
    public Page<SubjectRegistrationRequest> getRequestsForFaculty(Faculty faculty, Pageable pageable) {
        return subjectRegistrationRepository.findByFaculty(faculty,pageable);
    }
//<============== Get requests for a faculty end <===============





//===============> Get request of a student for a subject start ===============>
    @Override
    public SubjectRegistrationRequest getRequestOfStudentForSubject(Student student, Subject subject) {
        return subjectRegistrationRepository.findByStudentAndSubject(student,subject);
    }
//<============== Get request of a student for a subject end <===============





//===============> Get all requests of a student start ===============>
    @Override
    public List<SubjectRegistrationRequest> getRequestOfStudent(Student student) {
        return subjectRegistrationRepository.findByStudent(student);
    }
//<==============  Get all requests of a student end <===============





//===============> Get all requests of a students with status is not rejected start ===============>
    @Override
    public List<SubjectRegistrationRequest> getRequestsOfStudentWithNotRejected(Student student) {
        return subjectRegistrationRepository.findByStudentWithNotRejected(student);
    }
//<============== Get all requests of a students with status is not rejected end <===============
}
