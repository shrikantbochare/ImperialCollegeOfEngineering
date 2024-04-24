package com.ICE.ControllerAdvices;


import com.ICE.Entities.Faculty;
import com.ICE.Entities.Student;
import com.ICE.Service.ServiceFacultyDao;
import com.ICE.Service.ServiceStudentDao;
import com.ICE.controllers.FacultyController;
import com.ICE.controllers.StudentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice(basePackageClasses = {StudentController.class, FacultyController.class})
public class ConAd2 {



    private ServiceStudentDao serviceStudentDao;
    private ServiceFacultyDao serviceFacultyDao;


    @Autowired
    public ConAd2(ServiceStudentDao serviceStudentDao, ServiceFacultyDao serviceFacultyDao) {
        this.serviceStudentDao = serviceStudentDao;
        this.serviceFacultyDao = serviceFacultyDao;
    }



    @ModelAttribute
    public void addCurrentUser(Model model, Principal p) {
        if (p != null) {
            String username = p.getName();

//            Student student = (Student) serviceStudentDao.getStudentByUniversityNo(username);
//            Faculty faculty = (Faculty) serviceFacultyDao.getFacultyByFacultyId(username);
//
//
//            if ((student == null) && (faculty != null)) {
//                model.addAttribute("currentUser", faculty);
//            } else {
//                model.addAttribute("currentUser", student);
//            }



            if(username.startsWith("BT"))
            {
                Student student = (Student) serviceStudentDao.getStudentByUniversityNo(username);
                model.addAttribute("currentUser", student);
            }
            else
            {
                Faculty faculty = (Faculty) serviceFacultyDao.getFacultyByFacultyId(username);
                model.addAttribute("currentUser", faculty);
            }

        }
        System.out.println("hello===========>");

    }
}
