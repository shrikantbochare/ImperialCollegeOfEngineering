//package com.ICE.Aspects;
//
//
//import com.ICE.Entities.Faculty;
//import com.ICE.Entities.Student;
//import com.ICE.Service.ServiceFacultyDao;
//import com.ICE.Service.ServiceStudentDao;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.ui.Model;
//
//import java.security.Principal;
//
//@Aspect
//@Component
//public class CurrentUserAspect {
//
//
//    private ServiceStudentDao serviceStudentDao;
//    private ServiceFacultyDao serviceFacultyDao;
//
//
//    @Autowired
//    public CurrentUserAspect(ServiceStudentDao serviceStudentDao, ServiceFacultyDao serviceFacultyDao) {
//        this.serviceStudentDao = serviceStudentDao;
//        this.serviceFacultyDao = serviceFacultyDao;
//    }
//
//
//    @Pointcut("execution(String com.ICE.controllers.HomeController.login(..)) && args(model,p,..)")
//    private void forAddingCurrentUser(Model model, Principal p){}
//
//
//    @AfterReturning("forAddingCurrentUser(model,p)")
//    public void addCurrentUserToModel(Model model, Principal p)
//    {
//        if(p != null)
//        {
//            String username = p.getName();
//
//            Student student = (Student) serviceStudentDao.getStudentByUniversityNo(username);
//            Faculty faculty = (Faculty) serviceFacultyDao.getFacultyByFacultyId(username);
//
//
//            if((student == null) && (faculty != null))
//            {
//                model.addAttribute("currentUser",faculty);
//            }
//            else
//            {
//                model.addAttribute("currentUser",student);
//            }
//
//
//        }
//        System.out.println("hello==================================================================>");
//        System.out.println(model.getAttribute("currentUser"));
//    }
//}
