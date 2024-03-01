package com.ICE.Service;

import com.ICE.Entities.Score;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;

import java.util.List;

public interface ServiceScoreDao {


    void saveScore(Score score);


    List<Score> getScoresOfStudent(Student student);

    List<Score> getScoreForSubject(Subject subject);
}
