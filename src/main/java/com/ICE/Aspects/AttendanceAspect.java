package com.ICE.Aspects;


import com.ICE.Entities.Attendance;
import com.ICE.Service.ServiceAttendanceDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
@Aspect
public class AttendanceAspect {


    ServiceAttendanceDao serviceAttendanceDao;

    @Autowired
    public AttendanceAspect(ServiceAttendanceDao serviceAttendanceDao) {
        this.serviceAttendanceDao=serviceAttendanceDao;
    }


    @AfterReturning("execution(String com.ICE.controllers.FacultyController.updateSubjectAttendance2(..))")
    public void generatePercentage(JoinPoint joinPoint)
    {
        Object[] objs = joinPoint.getArgs();
        Attendance attendance = (Attendance) objs[0];

        Attendance newAttendance = serviceAttendanceDao.getAttendanceById(attendance.getId());
        if(newAttendance.getTotalClasses() > 0)
        {
            int totalClasses, presentClasses;
            float percentage;
            totalClasses = newAttendance.getTotalClasses();
            presentClasses = newAttendance.getPresentClasses();
            percentage = ((float) presentClasses /totalClasses) * 100;

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String formattedPercentage = decimalFormat.format(percentage);
            percentage = Float.parseFloat(formattedPercentage);

            newAttendance.setPresentPercentage(percentage);
            serviceAttendanceDao.saveAttendance(newAttendance);
        }

    }
}
