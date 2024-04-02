package com.ICE.DAO;

import com.ICE.Entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Optional<Student> findByUniversityNo(String universityNo);


//    @Query("Select s from Student s where s.subjects.id=:data")
//    Page<Student> findBySubjectId(@Param("data") int id, Pageable pageable);
}
