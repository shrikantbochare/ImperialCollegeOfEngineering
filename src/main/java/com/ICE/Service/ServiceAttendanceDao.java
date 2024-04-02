package com.ICE.Service;

import com.ICE.Entities.Attendance;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceAttendanceDao {

    void saveAttendance(Attendance attendance);

    Page<Attendance> getAttendanceOfStudent(Student student,Pageable pageable);


    Page<Attendance> getAttendanceOfSubject(Subject subject, Pageable pageable);

    Attendance getAttendanceById(int id);

    void deleteAttendanceOfSubject(Subject subject);
}
