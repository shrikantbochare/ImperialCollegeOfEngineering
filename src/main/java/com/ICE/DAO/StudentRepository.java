package com.ICE.DAO;

import com.ICE.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Optional<Student> findByUniversityNo(String universityNo);
}
