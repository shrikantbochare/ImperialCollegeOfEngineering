package com.ICE.controllers;

import com.ICE.DAO.FacultyRepository;
import com.ICE.Entities.*;
import com.ICE.Pojo.FacultyPojo;
import com.ICE.Pojo.SubjectPojo;
import com.ICE.Service.*;
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
    private ServiceAttendanceDao serviceAttendanceDao;
    private ServiceScoreDao serviceScoreDao;
    private Service1 service1;
    private ServiceSubjectRegistrationDao serviceSubjectRegistrationDao;
    private ServiceStudentDao serviceStudentDao;

    private ServiceSubjectDao serviceSubjectDao;



    @Autowired
    public FacultyController(ServiceFacultyDao serviceFacultyDao,Service1 service1,ServiceProfilePicDao serviceProfilePicDao
    ,ServiceSubjectDao serviceSubjectDao,ServiceAttendanceDao serviceAttendanceDao,ServiceScoreDao serviceScoreDao,
                             ServiceSubjectRegistrationDao serviceSubjectRegistrationDao,ServiceStudentDao serviceStudentDao) {
        this.serviceFacultyDao = serviceFacultyDao;
        this.service1=service1;
        this.serviceProfilePicDao=serviceProfilePicDao;
        this.serviceSubjectDao=serviceSubjectDao;
        this.serviceAttendanceDao=serviceAttendanceDao;
        this.serviceScoreDao=serviceScoreDao;
        this.serviceSubjectRegistrationDao=serviceSubjectRegistrationDao;
        this.serviceStudentDao=serviceStudentDao;
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
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        List<Subject> subjects = serviceSubjectDao.getSubjectsOfFaculty(faculty);

        model.addAttribute("subjects",subjects);
        model.addAttribute("PageName","FacultySubjects");
        model.addAttribute("attendanceView","view");
        return "Template";
    }





    @GetMapping("/attendance")
    public  String facultyAttendance(Model model)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        List<Subject> subjects = serviceSubjectDao.getSubjectsOfFaculty(faculty);

        model.addAttribute("subjects",subjects);
        model.addAttribute("PageName","FacultySubjects");
        model.addAttribute("attendanceUpdate","update");
        return "Template";
    }




    @GetMapping("/subjects/attendance/view")
    public String viewSubjectAttendance(@RequestParam("subject") Integer id, Model model)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);
        List<Attendance> attendances = serviceAttendanceDao.getAttendanceOfSubject(subject);

        model.addAttribute("attendances",attendances);
        model.addAttribute("subjectName",subject);
        model.addAttribute("PageName","FacultyAttendance");
        return "Template";
    }



    @GetMapping("/subjects/attendance/update")
    public String updateSubjectAttendance(@RequestParam("subject") Integer id,Model model)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);
        List<Attendance> attendances = serviceAttendanceDao.getAttendanceOfSubject(subject);

        model.addAttribute("attendances",attendances);
        model.addAttribute("subjectName",subject);
        model.addAttribute("PageName","FacultyAttendanceUpdate");
        return "Template";
    }





//    @PostMapping("/subjects/attendance/update/process")
//    public String updateSubjectAttendance2()
//    {
//
//    }




    @GetMapping("/exams")
    public  String facultyExams(Model model)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        List<Subject> subjects = serviceSubjectDao.getSubjectsOfFaculty(faculty);

        model.addAttribute("subjects",subjects);
        model.addAttribute("PageName","FacultySubjects");
        model.addAttribute("studentMarks","studentMarks");
        return "Template";
    }



    @GetMapping("/exams/view")
    public String viewStudentMarks(@RequestParam("subject") Integer id , Model model)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);
        List<Score> scores = serviceScoreDao.getScoreForSubject(subject);

        model.addAttribute("scores",scores);
        model.addAttribute("subjectName",subject);
        model.addAttribute("PageName","StudentMarksView");
        return "Template";
    }





// ================ERROR
    @GetMapping("/exams/update")
    public String viewStudentUpdate(@RequestParam("subject") Integer id ,Model model)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);
        List<Score> scores = serviceScoreDao.getScoreForSubject(subject);

        model.addAttribute("scores",scores);
        model.addAttribute("subjectName",subject);
        model.addAttribute("PageName","StudentMarksUpdate");
        return "Template";
    }
// =====================ERROR




    @PostMapping("/exams/update/process")
    public String studentScoreUpdate(@ModelAttribute("score") Score score)
    {
        serviceScoreDao.saveScore(score);
        return "redirect:/faculty/exams/update";
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
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        List<SubjectRegistrationRequest>  requests = serviceSubjectRegistrationDao.getRequestsForFaculty(faculty);
        System.out.println(requests);

        model.addAttribute("Requests",requests);
        model.addAttribute("PageName","FacultyRequests");
        return "Template";
    }





    @GetMapping("/requests/approve")
    public String approveSubRegistrationRequest(@RequestParam("stId") int studentId, @RequestParam("subId") int subjectId)
    {
        Student student1 = serviceStudentDao.getStudentById(studentId);
        Subject subject =serviceSubjectDao.getSubjectById(subjectId);

        student1.addSubjects(subject);
        subject.addStudent(student1);


        Attendance attendance = new Attendance(0,0,0,null);
        attendance.setStudent(student1);
        attendance.setSubject(subject);

        student1.addAttendance(attendance);
        subject.addAttendance(attendance);


        Score score = new Score(0,0,0,0,null);
        score.setStudent(student1);
        score.setSubject(subject);


        student1.addScore(score);
        subject.addScore(score);

        serviceStudentDao.saveStudent(student1);
        serviceSubjectDao.save(subject);
        serviceAttendanceDao.saveAttendance(attendance);
        serviceScoreDao.saveScore(score);
        serviceSubjectRegistrationDao.deleteSubjectRegistrationRequest(student1,subject);


        return "redirect:/faculty/requests";
    }





    @GetMapping("/requests/reject")
    public String rejectSubRegistrationRequest(@RequestParam("stId") int studentId, @RequestParam("subId") int subjectId)
    {
        Student student1 = serviceStudentDao.getStudentById(studentId);
        Subject subject =serviceSubjectDao.getSubjectById(subjectId);


        SubjectRegistrationRequest request = serviceSubjectRegistrationDao.getRequestOfStudentForSubject(student1,subject);
        request.setStatus("Rejected");
        serviceSubjectRegistrationDao.saveSubjectRegistrationRequest(request);

        return "redirect:/faculty/requests";
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
        subject.setFaculty(faculty);

        faculty.addSubjects(subject);

        serviceSubjectDao.save(subject);
        serviceFacultyDao.saveFaculty(faculty);
        return "redirect:/faculty/manageSubjects";
    }
}
