package com.ICE.controllers;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.ProfilePic;
import com.ICE.Entities.Student;
import com.ICE.Pojo.FacultyPojo;
import com.ICE.Pojo.StudentPojo;
import com.ICE.Service.ServiceFacultyDao;
import com.ICE.Service.ServiceStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {




    private ServiceStudentDao serviceStudentDao;
    private ServiceFacultyDao serviceFacultyDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public HomeController(ServiceStudentDao serviceStudentDao, ServiceFacultyDao serviceFacultyDao,PasswordEncoder passwordEncoder) {
        this.serviceStudentDao = serviceStudentDao;
        this.serviceFacultyDao = serviceFacultyDao;
        this.passwordEncoder=passwordEncoder;
    }






// =============> Home handler Start ==============>
    @GetMapping("/")
    public String test(Model model)
    {
        model.addAttribute("PageName","Home");
        return "Template";
    }
// <============= Home handler End <==============





// =============> Contact handler Start ==============>
    @GetMapping("/contact")
    public String contact(Model model)
    {
        model.addAttribute("PageName","contact");
        return "Template";
    }
// <============= Contact handler End <==============





// ===========> Department handlers Start ==============>
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
// <=========== Department handlers End <==============





// =============> Login handler Start ==============>
    @GetMapping("/login")
    public String studentLogin(Model model)
    {
        model.addAttribute("PageName","login");
        return "Template";
    }
// <============= Login handler End <==============





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
        StudentPojo studentPojo = new StudentPojo();
        model.addAttribute("student",studentPojo);
        return "Template";
    }





    @PostMapping("/student/registrationDetails")
    public String studentRegDetails(@ModelAttribute("student") StudentPojo studentPojo)
    {
        Student student = new Student(studentPojo.getName(),studentPojo.getEmail(),studentPojo.getUniversityNo(),passwordEncoder.encode(studentPojo.getPassword()),studentPojo.getDepartment(),studentPojo.getCourse());
        student.setRole("ROLE_STUDENT");
        ProfilePic profilePic = new ProfilePic("default_pic.jpg");
        profilePic.setStudent(student);
        student.setProfilePic(profilePic);

        Faculty faculty = serviceFacultyDao.getClassTeacher(student.getDepartment(),student.getCourse());
        faculty.addStudent(student);
        serviceFacultyDao.saveFaculty(faculty);

        student.setFaculty(faculty);
        serviceStudentDao.saveStudent(student);
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
        FacultyPojo facultyPojo = new FacultyPojo();
        model.addAttribute("faculty",facultyPojo);
        return "Template";
    }





    @PostMapping("/faculty/registrationDetails")
    public String FacultyRegDetails(@ModelAttribute("faculty") FacultyPojo facultyPojo)
    {
        Faculty faculty = new Faculty(facultyPojo.getName(),facultyPojo.getFacultyId(),facultyPojo.getEmail(),passwordEncoder.encode(facultyPojo.getPassword()));
        faculty.addRole("ROLE_FACULTY");
        ProfilePic profilePic = new ProfilePic("default_pic.jpg");
        profilePic.setFaculty(faculty);
        faculty.setProfilePic(profilePic);
        serviceFacultyDao.saveFaculty(faculty);
        return "redirect:/home/login";
    }

// <================ Faculty Registration End <=================
}
