package com.ICE.DAO;

import com.ICE.Entities.Attendance;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {

    List<Attendance> findByStudent(Student student);

    List<Attendance> findBySubject(Subject subject);

    void deleteBySubject(Subject subject);
}
