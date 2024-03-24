package com.ICE.ControllerAdvices;


import com.ICE.Entities.Faculty;
import com.ICE.Entities.Student;
import com.ICE.Service.ServiceFacultyDao;
import com.ICE.Service.ServiceStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.thymeleaf.exceptions.TemplateInputException;

import java.io.IOException;
import java.security.Principal;

@ControllerAdvice
public class ConAd1 {




    private ServiceStudentDao serviceStudentDao;
    private ServiceFacultyDao serviceFacultyDao;


    @Autowired
    public ConAd1(ServiceStudentDao serviceStudentDao, ServiceFacultyDao serviceFacultyDao) {
        this.serviceStudentDao = serviceStudentDao;
        this.serviceFacultyDao = serviceFacultyDao;
    }


    @ModelAttribute
    public void addCurrentUser(Model model, Principal p) {
        if (p != null) {
            String username = p.getName();

            Student student = (Student) serviceStudentDao.getStudentByUniversityNo(username);
            Faculty faculty = (Faculty) serviceFacultyDao.getFacultyByFacultyId(username);


            if ((student == null) && (faculty != null)) {
                model.addAttribute("currentUser", faculty);
            } else {
                model.addAttribute("currentUser", student);
            }

        }
        System.out.println("hello===========>");
    }





// =============> Exception handler for failed Mail =================>
//    @org.springframework.web.bind.annotation.ExceptionHandler(MessagingException.class)
//    public String mailException(MessagingException e, Model model)
//    {
//        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.SERVICE_UNAVAILABLE);
//
//        problemDetail.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
//        problemDetail.setDetail("Message Not Sent");
//
//        model.addAttribute("mailNotSent",problemDetail);
//        return "redirect:/registerEmail";
//
//    }
// <============ Exception handler for failed Mail <================






// =============> Exception handler IO Exception =================>
    @org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
    public String IoException(IOException e,Model model)
    {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail("Bad Request");
        problemDetail.setStatus(HttpStatus.BAD_REQUEST);

        model.addAttribute("PageName","Exception");
        return "Template";
    }
// <============= Exception handler IO Exception <=================






// =============> Exception handler global Exception =================>
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String parentException(Exception e,Model model)
    {
        model.addAttribute("PageName","Exception");
        return "Template";
    }
// <============= Exception handler global Exception <=================





// =============> Exception handler TemplateInputException  =================>
    @org.springframework.web.bind.annotation.ExceptionHandler(TemplateInputException.class)
    public String templateInputExceptionHandler(TemplateInputException t,Model model)
    {
        model.addAttribute("PageName","Exception");
        return "Template";
    }
// <============= Exception handler TemplateInputException <=================

}
