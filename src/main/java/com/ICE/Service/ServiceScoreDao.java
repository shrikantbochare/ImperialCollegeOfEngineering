package com.ICE.Service;

import com.ICE.Entities.Score;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceScoreDao {


    void saveScore(Score score);


    Page<Score> getScoresOfStudent(Student student,Pageable pageable);

    Page<Score> getScoreForSubject(Subject subject, Pageable pageable);

    Score getScoreById(int id);

    void removeScoresOfSubject(Subject subject);
}
