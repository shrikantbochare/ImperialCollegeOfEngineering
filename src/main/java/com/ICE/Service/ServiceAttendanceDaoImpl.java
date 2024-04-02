package com.ICE.Service;


import com.ICE.DAO.AttendanceRepository;
import com.ICE.Entities.Attendance;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Attendance> getAttendanceOfStudent(Student student, Pageable pageable) {
        return attendanceRepository.findByStudent(student,pageable);
    }
//<============== Get attendance for all subjects of a student end <===============





//===============> Get attendance for all students of a subject start ===============>
    @Override
    public Page<Attendance> getAttendanceOfSubject(Subject subject, Pageable pageable) {
        return attendanceRepository.findBySubject(subject,pageable);
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




//===============> Remove Attendances of a subject start ===============>
    @Override
    public void deleteAttendanceOfSubject(Subject subject) {
        attendanceRepository.deleteBySubject(subject);
    }
//<============== Remove Attendances of a subject end <===============

}
