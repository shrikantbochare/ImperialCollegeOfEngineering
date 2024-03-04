package com.ICE.Service;


import com.ICE.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFacultyImpl implements ServiceFaculty{


    private ServiceStudentDao serviceStudentDao;
    private ServiceSubjectDao serviceSubjectDao;
    private ServiceAttendanceDao serviceAttendanceDao;
    private ServiceScoreDao serviceScoreDao;
    private ServiceSubjectRegistrationDao serviceSubjectRegistrationDao;




    @Autowired
    public ServiceFacultyImpl(ServiceStudentDao serviceStudentDao, ServiceSubjectDao serviceSubjectDao,
                              ServiceAttendanceDao serviceAttendanceDao, ServiceScoreDao serviceScoreDao,
                              ServiceSubjectRegistrationDao serviceSubjectRegistrationDao) {
        this.serviceStudentDao = serviceStudentDao;
        this.serviceSubjectDao = serviceSubjectDao;
        this.serviceAttendanceDao = serviceAttendanceDao;
        this.serviceScoreDao = serviceScoreDao;
        this.serviceSubjectRegistrationDao = serviceSubjectRegistrationDao;
    }




//===============> Approve subject registration start ===============>
    @Override
    public void successSubjectRegistration(int stId, int subId) {
        Student student1 = serviceStudentDao.getStudentById(stId);
        Subject subject =serviceSubjectDao.getSubjectById(subId);

        student1.addSubjects(subject);
        subject.addStudent(student1);


        Attendance attendance = new Attendance(0,0,0,null);
        attendance.setStudent(student1);
        attendance.setSubject(subject);

        student1.addAttendance(attendance);
        subject.addAttendance(attendance);


        Score score = new Score(0,0,0,0,null);
        score.setStudent(student1);
        score.setSubject(subject);


        student1.addScore(score);
        subject.addScore(score);

        serviceStudentDao.saveStudent(student1);
        serviceSubjectDao.save(subject);
        serviceAttendanceDao.saveAttendance(attendance);
        serviceScoreDao.saveScore(score);
        serviceSubjectRegistrationDao.deleteSubjectRegistrationRequest(student1,subject);
    }
//<==============  Approve subject registration end <===============






//===============>  Reject subject registration start ===============>
    @Override
    public void rejectSubjectRegistration(int studentId, int subjectId) {
        Student student1 = serviceStudentDao.getStudentById(studentId);
        Subject subject =serviceSubjectDao.getSubjectById(subjectId);

        SubjectRegistrationRequest request = serviceSubjectRegistrationDao.getRequestOfStudentForSubject(student1,subject);
        request.setStatus("Rejected");
        serviceSubjectRegistrationDao.saveSubjectRegistrationRequest(request);
    }
//<==============  Reject subject registration end <===============
}
