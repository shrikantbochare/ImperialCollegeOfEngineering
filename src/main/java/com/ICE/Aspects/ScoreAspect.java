package com.ICE.Aspects;


import com.ICE.Entities.Score;
import com.ICE.Service.ServiceScoreDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ScoreAspect {


    ServiceScoreDao serviceScoreDao;


    @Autowired
    public ScoreAspect(ServiceScoreDao serviceScoreDao) {
        this.serviceScoreDao = serviceScoreDao;
    }

    @AfterReturning("execution(String com.ICE.controllers.FacultyController.studentScoreUpdate(..))")
    public void generateGrade(JoinPoint joinPoint)
    {
        System.out.println("start");
        Object[] objs = joinPoint.getArgs();
        Score score = (Score) objs[0];

        Score newScore = serviceScoreDao.getScoreById(score.getId());
        if((newScore.getCt1() != 0) && (newScore.getCt2() != 0) && (newScore.getInternal() != 0) && (newScore.getEndSem() != 0))
        {
            int totalScore , ct1, ct2, endSem, internal;
            char grade;
            ct1 = newScore.getCt1();
            ct2 = newScore.getCt2();
            endSem = newScore.getEndSem();
            internal = newScore.getInternal();
            totalScore = ct1 + ct2 + internal + endSem;
            if (totalScore > 90)
            {
                grade = 'A';
            } else if ((90 >= totalScore) && (totalScore > 75)) {
                grade = 'B';
            }
            else if((75 >= totalScore) && (totalScore > 50))
            {
                grade = 'C';
            }
            else
            {
                grade = 'D';
            }

            newScore.setGrade(String.valueOf(grade));
            serviceScoreDao.saveScore(newScore);
        }
        System.out.println("end");
    }
}
