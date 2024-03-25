package com.ICE.controllers;


import com.ICE.Entities.*;
import com.ICE.Pojo.QueryPojo;
import com.ICE.Pojo.StudentPojo;
import com.ICE.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {



    private ServiceStudentDao serviceStudentDao;
    private ServiceProfilePicDao serviceProfilePicDao;
    private ServiceSubjectDao serviceSubjectDao;
    private ServiceAttendanceDao serviceAttendanceDao;
    private ServiceScoreDao serviceScoreDao;
    private ServiceSubjectRegistrationDao serviceSubjectRegistrationDao;
    private Service1 service1;
    private ServiceStudent serviceStudent;
    private ServiceQueryDao serviceQueryDao;
    private ServiceFacultyDao serviceFacultyDao;


    @Autowired
    public StudentController(ServiceStudentDao serviceStudentDao,Service1 service1,ServiceProfilePicDao serviceProfilePicDao
    ,ServiceSubjectDao serviceSubjectDao,ServiceAttendanceDao serviceAttendanceDao,ServiceScoreDao serviceScoreDao,
                             ServiceSubjectRegistrationDao serviceSubjectRegistrationDao,ServiceStudent serviceStudent,
                             ServiceQueryDao serviceQueryDao,ServiceFacultyDao serviceFacultyDao) {
        this.serviceStudentDao = serviceStudentDao;
        this.service1=service1;
        this.serviceProfilePicDao=serviceProfilePicDao;
        this.serviceSubjectDao=serviceSubjectDao;
        this.serviceAttendanceDao=serviceAttendanceDao;
        this.serviceScoreDao=serviceScoreDao;
        this.serviceSubjectRegistrationDao=serviceSubjectRegistrationDao;
        this.serviceStudent=serviceStudent;
        this.serviceQueryDao=serviceQueryDao;
        this.serviceFacultyDao=serviceFacultyDao;
    }



//===============> Student profile page start ===============>
    @GetMapping("/profile")
    public String profile(Model model,@ModelAttribute("currentUser") Student student)
    {

        StudentPojo studentPojo = new StudentPojo(student.getName(),student.getEmail(),student.getUniversityNo(),student.getPassword()
                                        ,student.getBirthdate(),student.getAddress(),student.getCity()
                                        ,student.getState(),student.getDepartment(),student.getCourse(),student.getSemester(),student.getAge());


        model.addAttribute("PageName","StudentProfile");
        model.addAttribute("student",studentPojo);

        return "Template";
    }
//<============== Student profile page end <===============





//===============> Student profile update start  ===============>
    @PostMapping("/profile/update")
    public String updateStudentProfile(@ModelAttribute("Student") StudentPojo studentPojo,@ModelAttribute("currentUser") Student student)
    {
        student.setAge(studentPojo.getAge());
        student.setName(studentPojo.getName());
        student.setBirthdate(studentPojo.getBirthdate());
        student.setAddress(studentPojo.getAddress());
        student.setCity(studentPojo.getCity());
        student.setState(studentPojo.getState());
        student.setCourse(studentPojo.getCourse());
        student.setSemester(studentPojo.getSemester());

        serviceStudentDao.saveStudent(student);


        return "redirect:/student/profile";
    }
//<============== Student profile update end <===============





//===============> Student profile pic update start ===============>
    @PostMapping("/profile/updateProfilePic")
    public String updateProfilePic(@RequestParam("profile_img")MultipartFile multipartFile,@ModelAttribute("currentUser") Student student) throws IOException
    {
        ProfilePic profilePic = student.getProfilePic();
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
//<============== Student profile pic update end <===============





//===============> Student profile pic remove start ===============>
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

        return "redirect:/student/profile";

    }
//<============== Student profile pic remove end <===============





//===============> Student password update page start ===============>
    @GetMapping("/passwordUpdate")
    public String passwordUpdate(Model model)
    {
        model.addAttribute("PageName","StudentPassUpdate");
        return "Template";

    }
//<============== Student password update page end <===============





//===============> Student password update  start ===============>
    @PostMapping("/passwordUpdate/process")
    public String processNewPassword(@RequestParam("Password_old") String password_old,@RequestParam("Password_new") String password_new
            ,@ModelAttribute("currentUser") Student student,Model model)
    {
        boolean match = BCrypt.checkpw(password_old,student.getPassword());
        if(match)
        {
            if(service1.checkPassValidity(password_new))
            {
                student.setPassword(service1.encodePassword(password_new));
                serviceStudentDao.saveStudent(student);
                model.addAttribute("Match","match");
            }
            else
            {
                model.addAttribute("password_error","Password must contain" +
                        " at least one small letter, capital letter, number, and special character like @ or _  " +
                        "with minimum size of 8 letters");
            }
        }
        else
        {
            model.addAttribute("NotMatch","NotMatch");
        }
        model.addAttribute("PageName","StudentPassUpdate");
        return "Template";
    }
//<============== Student password update  end <===============





//===============> Student subjects page start ===============>
    @GetMapping("/subjects")
    public String subjects(Model model,@ModelAttribute("currentUser") Student student)
    {
        List<Subject> subjects = serviceStudent.subjectsAvailableForRegistration(student);

        List<SubjectRegistrationRequest> requestsWithStatus = serviceSubjectRegistrationDao.getRequestOfStudent(student);

        model.addAttribute("PageName","StudentSubjects");
        model.addAttribute("subjects",subjects);
        model.addAttribute("registeredSubjects",student.getSubjects());
        model.addAttribute("RegistrationStatus",requestsWithStatus);

        return "Template";
    }
//<==============  Student subjects page end <===============





//===============> Student subject register page start ===============>
    @GetMapping("/subjects/register")
    public String registerSubject(@ModelAttribute("sId") int id,Model model,@ModelAttribute("currentUser") Student student) {
        Subject subject =serviceSubjectDao.getSubjectById(id);


        SubjectRegistrationRequest subjectRegistrationRequest = new SubjectRegistrationRequest(student,subject,"Pending");
        serviceSubjectRegistrationDao.saveSubjectRegistrationRequest(subjectRegistrationRequest);

        student.addRequest(subjectRegistrationRequest);
        serviceStudentDao.saveStudent(student);

        subject.addRequest(subjectRegistrationRequest);
        serviceSubjectDao.save(subject);

        return "redirect:/student/subjects";
    }
//<==============  Student subject register page end <===============





//===============> Student exams view page start ===============>
    @GetMapping("/exams")
    public String exams(Model model,@ModelAttribute("currentUser") Student student)
    {
        List<Score> scores = serviceScoreDao.getScoresOfStudent(student);
        model.addAttribute("scores",scores);
        model.addAttribute("PageName","StudentExams");
        return "Template";
    }
//<============== Student exams view page end <===============





//===============> Student attendance view page start ===============>
    @GetMapping("/attendance")
    public String attendance(Model model,@ModelAttribute("currentUser") Student student)
    {
        List<Attendance> attendances = serviceAttendanceDao.getAttendanceOfStudent(student);

        model.addAttribute("attendances",attendances);
        model.addAttribute("PageName","StudentAttendance");
        return "Template";
    }
//<============== Student attendance view page end <===============





//===============> Student query page start ===============>
    @GetMapping("/query")
    public String query(Model model,@ModelAttribute("currentUser") Student student)
    {
        QueryPojo queryPojo = new QueryPojo();

        List<Query> queries = serviceQueryDao.getQueriesOfStudent(student);

        model.addAttribute("queries",queries);
        model.addAttribute("query",queryPojo);
        model.addAttribute("PageName","StudentQuery");
        return "Template";
    }
//<============== Student query page end <===============





//===============> Student query submit start ===============>
    @PostMapping("/query/submit")
    public String createQuery(@ModelAttribute("query") QueryPojo queryPojo,@ModelAttribute("currentUser") Student student)
    {
        Faculty faculty = student.getFaculty();

        Query query = new Query(queryPojo.getTitle(),queryPojo.getQuery(),service1.getTodayDate(),"Pending");
        query.setStudent(student);
        query.setFaculty(faculty);
        serviceQueryDao.saveQuery(query);


        student.addQuery(query);
        faculty.addQuery(query);

        serviceStudentDao.saveStudent(student);
        serviceFacultyDao.saveFaculty(faculty );

        return "redirect:/student/query";

    }
//<=============== Student query submit end <===============





//===============> Student query view page start ===============>
    @GetMapping("/query/view")
    public String queryView(@RequestParam("qId") int id,Model model)
    {
        Query query = serviceQueryDao.getQueryById(id);

        model.addAttribute("query",query);
        model.addAttribute("PageName","viewQuery");
        return "Template";
    }
//<============== Student query view page end <===============
}
