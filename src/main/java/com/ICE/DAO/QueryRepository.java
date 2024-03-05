package com.ICE.DAO;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.Query;
import com.ICE.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryRepository extends JpaRepository<Query,Integer> {


    List<Query> findByStudent(Student student);

    List<Query> findByFaculty(Faculty faculty);

    List<Query> findByFacultyAndStatus(Faculty faculty,String status);
}
