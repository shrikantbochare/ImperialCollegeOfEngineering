package com.ICE.Service;

import com.ICE.DAO.SubjectRegistrationRepository;
import com.ICE.Entities.Faculty;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import com.ICE.Entities.SubjectRegistrationRequest;

import java.util.List;

public interface ServiceSubjectRegistrationDao {

    void saveSubjectRegistrationRequest(SubjectRegistrationRequest subjectRegistrationRequest);


    void deleteSubjectRegistrationRequest(Student student, Subject subject);


    List<SubjectRegistrationRequest>  getRequestsForFaculty(Faculty faculty);


    SubjectRegistrationRequest getRequestOfStudentForSubject(Student student, Subject subject);


    List<SubjectRegistrationRequest> getRequestOfStudent(Student student);


    List<SubjectRegistrationRequest> getRequestsOfStudentWithNotRejected(Student student);
}
