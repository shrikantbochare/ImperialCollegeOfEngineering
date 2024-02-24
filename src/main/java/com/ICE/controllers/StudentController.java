package com.ICE.controllers;


import com.ICE.DAO.StudentRepository;
import com.ICE.Entities.ProfilePic;
import com.ICE.Entities.Student;
import com.ICE.Pojo.StudentPojo;
import com.ICE.Service.Service1;
import com.ICE.Service.ServiceProfilePicDao;
import com.ICE.Service.ServiceStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {



    private ServiceStudentDao serviceStudentDao;
    private ServiceProfilePicDao serviceProfilePicDao;
    private Service1 service1;


    @Autowired
    public StudentController(ServiceStudentDao serviceStudentDao,Service1 service1,ServiceProfilePicDao serviceProfilePicDao) {
        this.serviceStudentDao = serviceStudentDao;
        this.service1=service1;
        this.serviceProfilePicDao=serviceProfilePicDao;
    }




    @GetMapping("/profile")
    public String profile(Model model)
    {
        Student student1 = serviceStudentDao.getStudentById(4);

        StudentPojo studentPojo = new StudentPojo(student1.getName(),student1.getEmail(),student1.getUniversityNo(),student1.getPassword()
                                        ,student1.getBirthdate(),student1.getAddress(),student1.getCity()
                                        ,student1.getState(),student1.getDepartment(),student1.getCourse(),student1.getSemester(),student1.getAge());
        model.addAttribute("PageName","StudentProfile");
        model.addAttribute("student",studentPojo);
        model.addAttribute("currentUser",student1);

        return "Template";
    }




    @PostMapping("/profile/update")
    public String updateStudentProfile(@ModelAttribute("Student") StudentPojo studentPojo)
    {
        Student student1 = serviceStudentDao.getStudentById(4);

        student1.setAge(studentPojo.getAge());
        student1.setName(studentPojo.getName());
        student1.setBirthdate(studentPojo.getBirthdate());
        student1.setAddress(studentPojo.getAddress());
        student1.setCity(studentPojo.getCity());
        student1.setState(studentPojo.getState());
        student1.setDepartment(studentPojo.getDepartment());
        student1.setCourse(studentPojo.getCourse());
        student1.setSemester(studentPojo.getSemester());

        serviceStudentDao.saveStudent(student1);


        return "redirect:/student/profile";
    }





    @PostMapping("/profile/updateProfilePic")
    public String updateProfilePic(@RequestParam("profile_img")MultipartFile multipartFile) throws IOException
    {
        ProfilePic profilePic = serviceStudentDao.getStudentById(4).getProfilePic();
        if(!multipartFile.isEmpty())
        {
            if(!profilePic.getPic().equals("default_pic.jpg"))
            {
                service1.deleteProfilePicFromPath(profilePic.getPic());
            }
            service1.profilePicUpdate(profilePic,multipartFile);
        }
        return "redirect:/student/profile";
    }







    @GetMapping("/profile/removeProfilePic")
    public String removeProfilePic(@RequestParam("pId") int id) throws IOException
    {
        ProfilePic profilePic = serviceProfilePicDao.getProfilePicById(id);
        if(!profilePic.getPic().equals("default_pic.jpg"))
        {
            service1.deleteProfilePicFromPath(profilePic.getPic());
            profilePic.setPic("default_pic.jpg");
            serviceProfilePicDao.saveProfilePic(profilePic);
        }

        return "redirect:/faculty/profile";

    }


    @GetMapping("/subjects")
    public String subjects(Model model)
    {
        model.addAttribute("PageName","StudentSubjects");
        return "Template";
    }



    @GetMapping("/exams")
    public String exams(Model model)
    {
        model.addAttribute("PageName","StudentExams");
        return "Template";
    }




    @GetMapping("/attendance")
    public String attendance(Model model)
    {
        model.addAttribute("PageName","StudentAttendance");
        return "Template";
    }




    @GetMapping("/query")
    public String query(Model model)
    {
        model.addAttribute("PageName","StudentQuery");
        return "Template";
    }


    @GetMapping("/query/view")
    public String queryView(Model model)
    {
        model.addAttribute("PageName","viewQuery");
        return "Template";
    }
}
