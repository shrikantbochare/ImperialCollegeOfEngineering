package com.ICE.DAO;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.Query;
import com.ICE.Entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryRepository extends JpaRepository<Query,Integer> {


    Page<Query> findByStudent(Student student,Pageable pageable);

    List<Query> findByFaculty(Faculty faculty);

    Page<Query> findByFacultyAndStatus(Faculty faculty, String status, Pageable pageable);
}
