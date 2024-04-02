package com.ICE.Service;


import com.ICE.DAO.ScoreRepository;
import com.ICE.Entities.Score;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Score> getScoresOfStudent(Student student,Pageable pageable) {
        return scoreRepository.findByStudent(student,pageable);
    }
//<============== Get scores of a student for all subjects end <===============





//===============> Get scores of a subject for all students start ===============>
    @Override
    public Page<Score> getScoreForSubject(Subject subject, Pageable pageable) {
        return scoreRepository.findBySubject(subject,pageable);
    }
//<============== Get scores of a subject for all students end <===============




//===============> Get score by Id start ===============>
    @Override
    public Score getScoreById(int id) {
        Optional<Score> value = scoreRepository.findById(id);
        Score score = value.orElseGet(Score::new);
        return score;
    }
//<============== Get score by id end <===============




//===============> Remove Scores of a subject start ===============>
    @Override
    public void removeScoresOfSubject(Subject subject) {
        scoreRepository.deleteBySubject(subject);
    }
//<============== Remove Scores of a subject end <===============
}
