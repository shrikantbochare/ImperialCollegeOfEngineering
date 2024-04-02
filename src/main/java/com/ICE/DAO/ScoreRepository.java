package com.ICE.DAO;

import com.ICE.Entities.Score;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score,Integer> {


    Page<Score> findByStudent(Student student,Pageable pageable);

    Page<Score> findBySubject(Subject subject, Pageable pageable);

    void deleteBySubject(Subject subject);
}
