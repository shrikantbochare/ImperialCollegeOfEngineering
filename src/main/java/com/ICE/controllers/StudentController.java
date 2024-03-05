package com.ICE.controllers;


import com.ICE.Entities.*;
import com.ICE.Pojo.QueryPojo;
import com.ICE.Pojo.StudentPojo;
import com.ICE.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
//<============== Student profile page end <===============





//===============> Student profile update start  ===============>
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
        student1.setCourse(studentPojo.getCourse());
        student1.setSemester(studentPojo.getSemester());

        serviceStudentDao.saveStudent(student1);


        return "redirect:/student/profile";
    }
//<============== Student profile update end <===============





//===============> Student profile pic update start ===============>
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

        return "redirect:/faculty/profile";

    }
//<============== Student profile pic remove end <===============





//===============> Student subjects page start ===============>
    @GetMapping("/subjects")
    public String subjects(Model model)
    {
        Student student1 = serviceStudentDao.getStudentById(4);

        List<Subject> subjects = serviceStudent.subjectsAvailableForRegistration(student1);

        List<SubjectRegistrationRequest> requestsWithStatus = serviceSubjectRegistrationDao.getRequestOfStudent(student1);

        model.addAttribute("PageName","StudentSubjects");
        model.addAttribute("subjects",subjects);
        model.addAttribute("registeredSubjects",student1.getSubjects());
        model.addAttribute("RegistrationStatus",requestsWithStatus);

        return "Template";
    }
//<==============  Student subjects page end <===============





//===============> Student subject register page start ===============>
    @GetMapping("/subjects/register")
    public String registerSubject(@ModelAttribute("sId") int id,Model model) {
        Student student1 = serviceStudentDao.getStudentById(4);
        Subject subject =serviceSubjectDao.getSubjectById(id);


        SubjectRegistrationRequest subjectRegistrationRequest = new SubjectRegistrationRequest(student1,subject,"Pending");
        serviceSubjectRegistrationDao.saveSubjectRegistrationRequest(subjectRegistrationRequest);

        student1.addRequest(subjectRegistrationRequest);
        serviceStudentDao.saveStudent(student1);

        subject.addRequest(subjectRegistrationRequest);
        serviceSubjectDao.save(subject);

        return "redirect:/student/subjects";
    }
//<==============  Student subject register page end <===============





//===============> Student exams view page start ===============>
    @GetMapping("/exams")
    public String exams(Model model)
    {
        Student student1 = serviceStudentDao.getStudentById(4);
        List<Score> scores = serviceScoreDao.getScoresOfStudent(student1);
        model.addAttribute("scores",scores);
        model.addAttribute("PageName","StudentExams");
        return "Template";
    }
//<============== Student exams view page end <===============





//===============> Student attendance view page start ===============>
    @GetMapping("/attendance")
    public String attendance(Model model)
    {
        Student student1 = serviceStudentDao.getStudentById(4);
        List<Attendance> attendances = serviceAttendanceDao.getAttendanceOfStudent(student1);

        model.addAttribute("attendances",attendances);
        model.addAttribute("PageName","StudentAttendance");
        return "Template";
    }
//<============== Student attendance view page end <===============





//===============> Student query page start ===============>
    @GetMapping("/query")
    public String query(Model model)
    {
        Student student1 = serviceStudentDao.getStudentById(4);
        QueryPojo queryPojo = new QueryPojo();

        List<Query> queries = serviceQueryDao.getQueriesOfStudent(student1);

        model.addAttribute("queries",queries);
        model.addAttribute("query",queryPojo);
        model.addAttribute("PageName","StudentQuery");
        return "Template";
    }
//<============== Student query page end <===============





//===============> Student query submit start ===============>
    @PostMapping("/query/submit")
    public String createQuery(@ModelAttribute("query") QueryPojo queryPojo)
    {
        Student student1 = serviceStudentDao.getStudentById(4);
        Faculty faculty = student1.getFaculty();

        Query query = new Query(queryPojo.getTitle(),queryPojo.getQuery(),null,"Pending");
        query.setStudent(student1);
        query.setFaculty(faculty);
        serviceQueryDao.saveQuery(query);


        student1.addQuery(query);
        faculty.addQuery(query);

        serviceStudentDao.saveStudent(student1);
        serviceFacultyDao.saveFaculty(faculty);

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
