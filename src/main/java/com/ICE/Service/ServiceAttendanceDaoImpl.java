package com.ICE.Service;


import com.ICE.DAO.AttendanceRepository;
import com.ICE.Entities.Attendance;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAttendanceDaoImpl implements ServiceAttendanceDao{


    private AttendanceRepository attendanceRepository;


    @Autowired
    public ServiceAttendanceDaoImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }


    @Override
    public void saveAttendance(Attendance attendance) {
        attendanceRepository.save(attendance);
    }


    @Override
    public List<Attendance> getAttendanceOfStudent(Student student) {
        return attendanceRepository.findByStudent(student);
    }


    @Override
    public List<Attendance> getAttendanceOfSubject(Subject subject) {
        return attendanceRepository.findBySubject(subject);
    }
}
