package com.ICE.controllers;

import com.ICE.DAO.FacultyRepository;
import com.ICE.Entities.*;
import com.ICE.Pojo.FacultyPojo;
import com.ICE.Pojo.QueryPojo;
import com.ICE.Pojo.SubjectPojo;
import com.ICE.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private ServiceFaculty serviceFaculty;
    private ServiceQueryDao serviceQueryDao;





    @Autowired
    public FacultyController(ServiceFacultyDao serviceFacultyDao,Service1 service1,ServiceProfilePicDao serviceProfilePicDao
    ,ServiceSubjectDao serviceSubjectDao,ServiceAttendanceDao serviceAttendanceDao,ServiceScoreDao serviceScoreDao,
                             ServiceSubjectRegistrationDao serviceSubjectRegistrationDao,ServiceStudentDao serviceStudentDao,
                             ServiceFaculty serviceFaculty,ServiceQueryDao serviceQueryDao) {
        this.serviceFacultyDao = serviceFacultyDao;
        this.service1=service1;
        this.serviceProfilePicDao=serviceProfilePicDao;
        this.serviceSubjectDao=serviceSubjectDao;
        this.serviceAttendanceDao=serviceAttendanceDao;
        this.serviceScoreDao=serviceScoreDao;
        this.serviceSubjectRegistrationDao=serviceSubjectRegistrationDao;
        this.serviceStudentDao=serviceStudentDao;
        this.serviceFaculty=serviceFaculty;
        this.serviceQueryDao=serviceQueryDao;

    }





//===============> Faculty profile page start ===============>
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
//<============== Faculty profile page end <===============





//===============> Faculty profile update start ===============>
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
//<============== Faculty profile update end <===============





//===============> Faculty profile pic update start ===============>
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
//<============== Faculty profile pic update end <===============





//===============> Faculty profile pic remove start ===============>
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
//<============== Faculty profile pic remove end <===============





//===============> Faculty update department details page start ===============>
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
//<============== Faculty update department details page end <===============





//===============> Faculty update department details start ===============>
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
//<============== Faculty update department details end <===============





//===============> Faculty unallocated the subject start ===============>
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
//<==============  Faculty unallocated the subject end <===============





//===============> Faculty subjects page start ===============>
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
//<============== Faculty subjects page end <===============





//===============> Faculty attendance page start ===============>
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
//<============== Faculty attendance page end <===============





//===============> Faculty attendance view page start ===============>
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
//<============== Faculty attendance view page end <===============





//===============> Faculty attendance update page start ===============>
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
//<============== Faculty attendance update page end <===============





//==============> Faculty attendance update  start ===============>
    @PostMapping("/subjects/attendance/update/process")
    public String updateSubjectAttendance2(@ModelAttribute("attendance") Attendance attendance)
    {
        Attendance oldAttendance = serviceAttendanceDao.getAttendanceById(attendance.getId());
        oldAttendance.setPresentClasses(attendance.getPresentClasses());
        oldAttendance.setTotalClasses(attendance.getTotalClasses());
        oldAttendance.setUpdatedDate(null);

        serviceAttendanceDao.saveAttendance(oldAttendance);
        return "redirect:/faculty/subjects/attendance/update?subject="+ oldAttendance.getSubject().getId();
    }
//<============== Faculty attendance update  end <===============





//===============> Faculty exams  page start ===============>
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
//<============== Faculty exams  page end <===============





//===============> Faculty exams view page start ===============>
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
//<============== Faculty exams view page end <===============





//===============> Faculty exams update page start ===============>
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
//<============== Faculty exams update page end<===============





//===============> Faculty exams update start ===============>
    @PostMapping("/exams/update/process")
    public String studentScoreUpdate(@ModelAttribute("score") Score score)
    {
        Score oldScore = serviceScoreDao.getScoreById(score.getId());
        oldScore.setCt1(score.getCt1());
        oldScore.setCt2(score.getCt2());
        oldScore.setInternal(score.getInternal());
        oldScore.setEndSem(score.getEndSem());

        serviceScoreDao.saveScore(oldScore);
        return "redirect:/faculty/exams/update?subject="+ oldScore.getSubject().getId();
    }
//<============== Faculty exams update end <===============





//===============> Faculty query view page start ===============>
    @GetMapping("/query")
    public  String facultyQueries(Model model)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        List<Query> pendingQueries = serviceQueryDao.getQueriesOfFacultyWithPending(faculty,"Pending");
        List<Query> resolvedQueries = serviceQueryDao.getQueriesOfFacultyWithResolved(faculty,"Resolved");

        model.addAttribute("queries",pendingQueries);
        model.addAttribute("resolvedQueries",resolvedQueries);
        model.addAttribute("PageName","StudentQueriesView");
        return "Template";
    }
//<============== Faculty query view page end <===============





//===============> Faculty query resolve page start ===============>
    @GetMapping("/query/resolve")
    public String facultyQueryResolve(Model model,@RequestParam("qId") int id)
    {
        Query query = serviceQueryDao.getQueryById(id);
        QueryPojo queryPojo = new QueryPojo();
        queryPojo.setId(query.getId());

        model.addAttribute("query",query);
        model.addAttribute("queryPojo",queryPojo);
        model.addAttribute("PageName","FacultyQueryResolve");
        return "Template";
    }
//<============== Faculty query resolve page end <===============





//===============> Faculty query resolve start ===============>
    @PostMapping("/query/resolve/submit")
    public String facultyQuerySubmit(@ModelAttribute("queryPojo") QueryPojo queryPojo)
    {
        Query query = serviceQueryDao.getQueryById(queryPojo.getId());
        query.setRemark(queryPojo.getRemark());
        query.setResolvedDate(null);
        query.setStatus("Resolved");

        serviceQueryDao.saveQuery(query);

        return "redirect:/faculty/query";
    }
//<============== Faculty query resolve  end <===============





//===============> Faculty requests view page start ===============>
    @GetMapping("/requests")
    public  String facultyRequests(Model model)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        List<SubjectRegistrationRequest>  requests = serviceSubjectRegistrationDao.getRequestsForFaculty(faculty);

        model.addAttribute("Requests",requests);
        model.addAttribute("PageName","FacultyRequests");
        return "Template";
    }
//<============== Faculty requests view page end <===============





//===============> Faculty requests approve start ===============>
    @GetMapping("/requests/approve")
    public String approveSubRegistrationRequest(@RequestParam("stId") int studentId, @RequestParam("subId") int subjectId)
    {
        serviceFaculty.successSubjectRegistration(studentId,subjectId);
        return "redirect:/faculty/requests";
    }
//<============== Faculty requests approve start <===============





//===============> Faculty requests reject start ===============>
    @GetMapping("/requests/reject")
    public String rejectSubRegistrationRequest(@RequestParam("stId") int studentId, @RequestParam("subId") int subjectId)
    {
        serviceFaculty.rejectSubjectRegistration(studentId,subjectId);
        return "redirect:/faculty/requests";
    }
//<============== Faculty requests reject end <===============





//===============> HOD-Faculty manage subjects page start ===============>
    @GetMapping("/manageSubjects")
    public String manageSubjects(Model model)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        List<Subject> subjects = serviceSubjectDao.getAllSubjectsOfDepartment(faculty.getDepartment());
        model.addAttribute("subjects",subjects);
        model.addAttribute("PageName","FacultyManageSubjects");
        return "Template";
    }
//<============== HOD-Faculty manage subjects page end <===============





//===============> HOD-Faculty manage subjects add new page start ===============>
    @GetMapping("/manageSubjects/add")
    public String addSubject(Model model)
    {
        SubjectPojo subjectPojo = new SubjectPojo();
        model.addAttribute("subject",subjectPojo);
        model.addAttribute("PageName","FacultyAddSubject");
        return "Template";
    }
//<============== HOD-Faculty manage subjects page add new end <===============





//===============> HOD-Faculty manage subjects add new start  ===============>
    @PostMapping("/manageSubjects/add/newSubject")
    public String addSubject2(@ModelAttribute("subject") SubjectPojo subjectPojo)
    {
        Faculty faculty = serviceFacultyDao.getFacultyById(4);
        Subject subject = new Subject(subjectPojo.getName(),subjectPojo.getSubId(),faculty.getDepartment(),
                subjectPojo.getCourse(),subjectPojo.getSemester(), subjectPojo.getCredits());


        serviceSubjectDao.save(subject);

        return "redirect:/faculty/manageSubjects";
    }
//<============== HOD-Faculty manage subjects add new end <===============




//===============> HOD-Faculty manage subjects edit page start  ===============>
    @GetMapping("/manageSubjects/edit")
    public String editSubject(@RequestParam("sId") int id,Model model)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);
        SubjectPojo subjectPojo = new SubjectPojo(subject.getName(),subject.getSubId(),subject.getDepartment(),subject.getCourse()
                                        ,subject.getSemester(),subject.getCredits());

        subjectPojo.setId(subject.getId());

        model.addAttribute("subject",subjectPojo);
        model.addAttribute("PageName","FacultyEditSubject");
        return "Template";
    }
//<============== HOD-Faculty manage subjects edit page end <===============




//===============> HOD-Faculty manage subjects edit  start  ===============>
    @PostMapping("/manageSubjects/edit/process")
    public String editSubject2(@ModelAttribute("subject") SubjectPojo subjectPojo)
    {
        Subject oldSubject = serviceSubjectDao.getSubjectById(subjectPojo.getId());

        oldSubject.setName(subjectPojo.getName());
        oldSubject.setSubId(subjectPojo.getSubId());
        oldSubject.setCourse(subjectPojo.getCourse());
        oldSubject.setSemester(subjectPojo.getSemester());
        oldSubject.setCredits(subjectPojo.getCredits());

        serviceSubjectDao.save(oldSubject);


        return "redirect:/faculty/manageSubjects";
    }
//<============== HOD-Faculty manage subjects edit  end <===============
}
