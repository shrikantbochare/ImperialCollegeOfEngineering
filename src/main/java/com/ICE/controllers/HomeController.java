package com.ICE.controllers;

import com.ICE.DAO.StudentRepository;
import com.ICE.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {




    private StudentRepository studentRepository;

    @Autowired
    public HomeController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String test(Model model)
    {
        model.addAttribute("PageName","Home");
        return "Template";
    }



    @GetMapping("/contact")
    public String contact(Model model)
    {
        model.addAttribute("PageName","contact");
        return "Template";
    }



    @GetMapping("/dept/ce")
    public String civil(Model model)
    {
        model.addAttribute("DeptName","Civil Engineering");
        model.addAttribute("PageName","dept");
        return "Template";
    }



    @GetMapping("/dept/me")
    public String mech(Model model)
    {
        model.addAttribute("DeptName","Mechanical Engineering");
        model.addAttribute("PageName","dept");
        return "Template";
    }



    @GetMapping("/dept/ee")
    public String electrical(Model model)
    {
        model.addAttribute("DeptName","Electrical Engineering");
        model.addAttribute("PageName","dept");
        return "Template";
    }



    @GetMapping("/dept/cse")
    public String computer(Model model)
    {
        model.addAttribute("DeptName","Computer Science And Engineering");
        model.addAttribute("PageName","dept");
        return "Template";
    }



    @GetMapping("/dept/it")
    public String info(Model model)
    {
        model.addAttribute("DeptName","Information Technology");
        model.addAttribute("PageName","dept");
        return "Template";
    }



    @GetMapping("/dept/etc")
    public String electronics(Model model)
    {
        model.addAttribute("DeptName","Electronics and Communication");
        model.addAttribute("PageName","dept");
        return "Template";
    }

    @GetMapping("/login")
    public String studentLogin(Model model)
    {
        model.addAttribute("PageName","login");
        return "Template";
    }







// ================> Student Registration Start =================>
    @GetMapping("/student/registration")
    public String studentRegistration(Model model)
    {
        model.addAttribute("PageName","Student_registration");
        return "Template";
    }






    @PostMapping("/student/registration/verification")
    public String emailVerification(Model model)
    {
        model.addAttribute("PageName","Student_registration_verification");
        return "Template";
    }





    @PostMapping("/student/registration/otpVerification")
    public String otpVerification(Model model)
    {
        model.addAttribute("PageName","Student_registration2");
        Student student = new Student();
        model.addAttribute("student",student);
        return "Template";
    }






    @PostMapping("/student/registrationDetails")
    public String studentRegDetails(@ModelAttribute("student") Student student)
    {
        studentRepository.save(student);
        return "redirect:/home/login";
    }

// <================ Student Registration End <=================








// ================> Faculty Registration Start =================>
    @GetMapping("/faculty/registration")
    public String facultyRegistration(Model model)
    {
        model.addAttribute("PageName","faculty_registration");
        return "Template";
    }






    @PostMapping("/faculty/registration/verification")
    public String emailVerification2(Model model)
    {
        model.addAttribute("PageName","Faculty_registration_verification");
        return "Template";
    }





    @PostMapping("/faculty/registration/otpVerification")
    public String otpVerification2(Model model)
    {
        model.addAttribute("PageName","Faculty_registration2");
        return "Template";
    }






    @PostMapping("/faculty/registrationDetails")
    public String FacultyRegDetails(Model model)
    {
        return "redirect:/login";
    }

// <================ Faculty Registration End <=================
}
