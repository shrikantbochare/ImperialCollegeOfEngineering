package com.ICE.Service;

import com.ICE.DAO.SubjectRegistrationRepository;
import com.ICE.Entities.Faculty;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import com.ICE.Entities.SubjectRegistrationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceSubjectRegistrationDao {

    void saveSubjectRegistrationRequest(SubjectRegistrationRequest subjectRegistrationRequest);


    void deleteSubjectRegistrationRequest(Student student, Subject subject);


    Page<SubjectRegistrationRequest> getRequestsForFaculty(Faculty faculty, Pageable pageable);


    SubjectRegistrationRequest getRequestOfStudentForSubject(Student student, Subject subject);


    List<SubjectRegistrationRequest> getRequestOfStudent(Student student);


    List<SubjectRegistrationRequest> getRequestsOfStudentWithNotRejected(Student student);
}
