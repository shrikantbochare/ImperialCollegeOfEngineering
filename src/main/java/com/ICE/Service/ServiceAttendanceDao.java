package com.ICE.Service;

import com.ICE.Entities.Attendance;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;

import java.util.List;

public interface ServiceAttendanceDao {

    void saveAttendance(Attendance attendance);

    List<Attendance> getAttendanceOfStudent(Student student);


    List<Attendance> getAttendanceOfSubject(Subject subject);

    Attendance getAttendanceById(int id);

    void deleteAttendanceOfSubject(Subject subject);
}
