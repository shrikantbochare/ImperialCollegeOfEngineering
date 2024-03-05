package com.ICE.Service;


import com.ICE.DAO.ScoreRepository;
import com.ICE.Entities.Score;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceScoreDaoImpl implements ServiceScoreDao{


    private ScoreRepository scoreRepository;


    @Autowired
    public ServiceScoreDaoImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }



//===============> Save or update score start ===============>
    @Override
    public void saveScore(Score score) {
        scoreRepository.save(score);
    }
//<============== Save or update score end  <===============





//===============> Get scores of a student for all subjects start ===============>
    @Override
    public List<Score> getScoresOfStudent(Student student) {
        return scoreRepository.findByStudent(student);
    }
//<============== Get scores of a student for all subjects end <===============





//===============> Get scores of a subject for all students start ===============>
    @Override
    public List<Score> getScoreForSubject(Subject subject) {
        return scoreRepository.findBySubject(subject);
    }
//<============== Get scores of a subject for all students end <===============




//===============> Get scores by Id start ===============>

    @Override
    public Score getScoreById(int id) {
        Optional<Score> value = scoreRepository.findById(id);
        Score score = value.orElseGet(Score::new);
        return score;
    }
}
