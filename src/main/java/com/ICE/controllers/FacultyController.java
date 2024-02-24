package com.ICE.controllers;

import com.ICE.DAO.FacultyRepository;
import com.ICE.Entities.Faculty;
import com.ICE.Entities.ProfilePic;
import com.ICE.Entities.Subject;
import com.ICE.Pojo.FacultyPojo;
import com.ICE.Pojo.SubjectPojo;
import com.ICE.Service.Service1;
import com.ICE.Service.ServiceFacultyDao;
import com.ICE.Service.ServiceProfilePicDao;
import com.ICE.Service.ServiceSubjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/faculty")
public class FacultyController {


    private ServiceFacultyDao serviceFacultyDao;
    private ServiceProfilePicDao serviceProfilePicDao;
    private Service1 service1;

    private ServiceSubjectDao serviceSubjectDao;



    @Autowired
    public FacultyController(ServiceFacultyDao serviceFacultyDao,Service1 service1,ServiceProfilePicDao serviceProfilePicDao
    ,ServiceSubjectDao serviceSubjectDao) {
        this.serviceFacultyDao = serviceFacultyDao;
        this.service1=service1;
        this.serviceProfilePicDao=serviceProfilePicDao;
        this.serviceSubjectDao=serviceSubjectDao;
    }






    @GetMapping("/profile")
    public String facultyProfile(Model model)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        FacultyPojo facultyPojo = new FacultyPojo(faculty.getName(),faculty.getFacultyId(),faculty.getEmail(),faculty.getPassword(),
                faculty.getAge(),faculty.getBirthdate(),faculty.getAddress(),faculty.getCity(),faculty.getState());
        model.addAttribute("PageName","FacultyProfile");
        model.addAttribute("faculty",facultyPojo);
        model.addAttribute("currentUser",faculty);

        return "Template";
    }






    @PostMapping("/profile/update")
    public String facultyProfileUpdate(@ModelAttribute("faculty")FacultyPojo facultyPojo)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);

        faculty.setName(facultyPojo.getName());
        faculty.setAge(facultyPojo.getAge());
        faculty.setBirthdate(facultyPojo.getBirthdate());
        faculty.setCity(facultyPojo.getCity());
        faculty.setState(facultyPojo.getState());
        faculty.setAddress(facultyPojo.getAddress());

        serviceFacultyDao.saveFaculty(faculty);


        return "redirect:/faculty/profile";
    }







    @PostMapping("/profile/updateProfilePic")
    public String updateProfilePic(@RequestParam("profile_img") MultipartFile multipartFile) throws IOException
    {
        ProfilePic profilePic = serviceFacultyDao.getFacultyById(4).getProfilePic();
        if(!multipartFile.isEmpty())
        {
            if(!profilePic.getPic().equals("default_pic.jpg"))
            {
                service1.deleteProfilePicFromPath(profilePic.getPic());
            }
            service1.profilePicUpdate(profilePic,multipartFile);
        }
        return "redirect:/faculty/profile";
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




//==================================

    @GetMapping("/updateDeptdetails")
    public String updateDeptdetails(Model model)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        FacultyPojo facultyPojo = new FacultyPojo(faculty.getClassTeacher(),faculty.getDesignation(),faculty.getDepartment());
        List<Subject> subjects = serviceSubjectDao.getAllSubjectsOfDepartmentNoFaculty(faculty.getDepartment());

        model.addAttribute("allSubjects",subjects);
        model.addAttribute("PageName","FacultyDeptUpdate");
        model.addAttribute("faculty",facultyPojo);
        model.addAttribute("currentUser",faculty);
        return "Template";
    }







    @PostMapping("/updateDeptdetails/process")
    public String processUpdateDeptDetails(@ModelAttribute("faculty") FacultyPojo facultyPojo,@RequestParam(value = "subjects", required = false) Set<Integer> subjects)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        faculty.setClassTeacher(facultyPojo.getClassTeacher());
        faculty.setDesignation(facultyPojo.getDesignation());
        faculty.setDepartment(facultyPojo.getDepartment());


        List<Subject> allocatedSubjects = serviceSubjectDao.getAllSubjectsWithIds(subjects);
        for(Subject subject : allocatedSubjects)
        {
            faculty.addSubjects(subject);
            subject.setFaculty(faculty);
            serviceSubjectDao.save(subject);
        }

        serviceFacultyDao.saveFaculty(faculty);

        return "redirect:/faculty/profile";

    }






    @GetMapping("/updateDeptdetails/removeSubject")
    public String removeAllocatedSubject(@RequestParam("sId") int id)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        Subject subject = serviceSubjectDao.getSubjectById(id);
        faculty.getSubjects().remove(subject);
        subject.setFaculty(null);

        serviceSubjectDao.save(subject);
        serviceFacultyDao.saveFaculty(faculty);

        return "redirect:/faculty/profile";
    }


    @GetMapping("/subjects")
    public  String facultySubjects(Model model)
    {
        model.addAttribute("PageName","FacultySubjects");
        model.addAttribute("attendanceView","view");
        return "Template";
    }





    @GetMapping("/attendance")
    public  String facultyAttendance(Model model)
    {
        model.addAttribute("PageName","FacultySubjects");
        model.addAttribute("attendanceUpdate","update");
        return "Template";
    }




    @GetMapping("/subjects/attendance/view")
    public String viewSubjectAttendance(Model model)
    {
        model.addAttribute("PageName","FacultyAttendance");
        return "Template";
    }



    @GetMapping("/subjects/attendance/update")
    public String updateSubjectAttendance(Model model)
    {
        model.addAttribute("PageName","FacultyAttendanceUpdate");
        return "Template";
    }





    @GetMapping("/exams")
    public  String facultyExams(Model model)
    {
        model.addAttribute("PageName","FacultySubjects");
        model.addAttribute("studentMarks","studentMarks");
        return "Template";
    }



    @GetMapping("/exams/view")
    public String viewStudentMarks(Model model)
    {
        model.addAttribute("PageName","StudentMarksView");
        return "Template";
    }






    @GetMapping("/exams/update")
    public String viewStudentUpdate(Model model)
    {
        model.addAttribute("PageName","StudentMarksUpdate");
        return "Template";
    }



    @GetMapping("/query")
    public  String facultyQueries(Model model)
    {
        model.addAttribute("PageName","StudentQueriesView");
        return "Template";
    }



    @GetMapping("/query/resolve")
    public String facultyQueryResolve(Model model)
    {
        model.addAttribute("PageName","FacultyQueryResolve");
        return "Template";
    }




    @GetMapping("/requests")
    public  String facultyRequests(Model model)
    {
        model.addAttribute("PageName","FacultyRequests");
        return "Template";
    }




    @GetMapping("/manageSubjects")
    public String manageSubjects(Model model)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        List<Subject> subjects = serviceSubjectDao.getAllSubjectsOfDepartment(faculty.getDepartment());
        model.addAttribute("subjects",subjects);
        model.addAttribute("PageName","FacultyManageSubjects");
        return "Template";
    }


    @GetMapping("/manageSubjects/add")
    public String addSubject(Model model)
    {
        SubjectPojo subjectPojo = new SubjectPojo();
        model.addAttribute("subject",subjectPojo);
        model.addAttribute("PageName","FacultyAddSubject");
        return "Template";
    }





    @PostMapping("/manageSubjects/add/newSubject")
    public String addSubject2(@ModelAttribute("subject") SubjectPojo subjectPojo)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        Subject subject = new Subject(subjectPojo.getName(),subjectPojo.getSubId(),faculty.getDepartment(),subjectPojo.getCourse(),subjectPojo.getSemester(), subjectPojo.getCredits());

        serviceSubjectDao.save(subject);
        return "redirect:/faculty/manageSubjects";
    }
}
