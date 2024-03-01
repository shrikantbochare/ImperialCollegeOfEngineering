package com.ICE.DAO;

import com.ICE.Entities.Score;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score,Integer> {


    List<Score> findByStudent(Student student);

    List<Score> findBySubject(Subject subject);
}
