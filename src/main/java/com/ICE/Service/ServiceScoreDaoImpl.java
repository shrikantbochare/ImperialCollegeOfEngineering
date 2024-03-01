package com.ICE.Service;


import com.ICE.DAO.ScoreRepository;
import com.ICE.Entities.Score;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceScoreDaoImpl implements ServiceScoreDao{


    private ScoreRepository scoreRepository;


    @Autowired
    public ServiceScoreDaoImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }


    @Override
    public void saveScore(Score score) {
        scoreRepository.save(score);
    }


    @Override
    public List<Score> getScoresOfStudent(Student student) {
        return scoreRepository.findByStudent(student);
    }


    @Override
    public List<Score> getScoreForSubject(Subject subject) {
        return scoreRepository.findBySubject(subject);
    }
}
