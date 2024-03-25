package com.ICE.controllers;


import com.ICE.Entities.Faculty;
import com.ICE.Entities.Student;
import com.ICE.Service.ServiceFacultyDao;
import com.ICE.Service.ServiceStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class ProfileController {


    private ServiceStudentDao serviceStudentDao;
    private ServiceFacultyDao serviceFacultyDao;


    @Autowired
    public ProfileController(ServiceStudentDao serviceStudentDao, ServiceFacultyDao serviceFacultyDao) {
        this.serviceStudentDao = serviceStudentDao;
        this.serviceFacultyDao = serviceFacultyDao;
    }


// =============> Student profile view page handler Start ==============>
    @GetMapping("/student")
    public String studentProfile(@RequestParam("id") int id, Model model)
    {
        Student student =  serviceStudentDao.getStudentById(id);

        model.addAttribute("profileOf",student);
        model.addAttribute("section","student");
        model.addAttribute("PageName","Profile");
        return "Template";
    }
// <============= Student profile view page handler End <==============





// =============> Faculty profile view page handler Start ==============>
    @GetMapping("/faculty")
    public String facultyProfile(@RequestParam("id") int id, Model model)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(id);

        model.addAttribute("profileOf",faculty);
        model.addAttribute("PageName","Profile");
        return "Template";
    }
// <============= Faculty profile view page handler End <==============
}
