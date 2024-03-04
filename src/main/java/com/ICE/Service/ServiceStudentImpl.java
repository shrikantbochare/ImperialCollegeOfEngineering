package com.ICE.Service;


import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import com.ICE.Entities.SubjectRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceStudentImpl implements ServiceStudent{


    private ServiceSubjectDao serviceSubjectDao;
    private ServiceSubjectRegistrationDao serviceSubjectRegistrationDao;


    @Autowired
    public ServiceStudentImpl(ServiceSubjectDao serviceSubjectDao, ServiceSubjectRegistrationDao serviceSubjectRegistrationDao) {
        this.serviceSubjectDao = serviceSubjectDao;
        this.serviceSubjectRegistrationDao = serviceSubjectRegistrationDao;
    }




//===============> Get subjects available for registration for a student start ===============>
    @Override
    public List<Subject> subjectsAvailableForRegistration(Student student) {
        List<Subject> subjects = serviceSubjectDao.getSubjectsForThisSemester(student.getDepartment(),student.getCourse(),student.getSemester());

        List<Subject> registeredSubjects = student.getSubjects();
        subjects.removeAll(registeredSubjects);

        List<SubjectRegistrationRequest> requests = serviceSubjectRegistrationDao.getRequestsOfStudentWithNotRejected(student);
        requests.forEach( (request) -> {
            Subject subject = request.getSubject();
            subjects.remove(subject);
        } );

        return  subjects;
    }
//<============== Get subjects available for registration for a student end <===============
}
