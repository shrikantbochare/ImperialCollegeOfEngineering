package com.ICE.Service;


import com.ICE.DAO.AttendanceRepository;
import com.ICE.Entities.Attendance;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAttendanceDaoImpl implements ServiceAttendanceDao{


    private AttendanceRepository attendanceRepository;


    @Autowired
    public ServiceAttendanceDaoImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }





//===============> Save or update attendance start ===============>
    @Override
    public void saveAttendance(Attendance attendance) {
        attendanceRepository.save(attendance);
    }
//<============== Save or update attendance end <===============





//===============> Get attendance for all subjects of a student start ===============>

    @Override
    public List<Attendance> getAttendanceOfStudent(Student student) {
        return attendanceRepository.findByStudent(student);
    }
//<============== Get attendance for all subjects of a student end <===============





//===============> Get attendance for all students of a subject start ===============>
    @Override
    public List<Attendance> getAttendanceOfSubject(Subject subject) {
        return attendanceRepository.findBySubject(subject);
    }
//<============== Get attendance for all students of a subject end <===============





//===============> Get attendance by id start ===============>
    @Override
    public Attendance getAttendanceById(int id) {
        Optional<Attendance> value = attendanceRepository.findById(id);
        Attendance attendance = value.orElseGet(Attendance::new);
        return attendance;
    }
//<============== Get attendance by id end <===============

}
