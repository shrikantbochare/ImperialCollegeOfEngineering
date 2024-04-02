package com.ICE.DAO;

import com.ICE.Entities.Attendance;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {

    Page<Attendance> findByStudent(Student student,Pageable pageable);

    Page<Attendance> findBySubject(Subject subject, Pageable pageable);

    void deleteBySubject(Subject subject);
}
