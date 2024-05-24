package com.ICE.controllers;

import com.ICE.DAO.FacultyRepository;
import com.ICE.DAO.ProfilePicRepository;
import com.ICE.Entities.*;
import com.ICE.Pojo.FacultyPojo;
import com.ICE.Pojo.QueryPojo;
import com.ICE.Pojo.SubjectPojo;
import com.ICE.Service.*;
import com.ICE.Validation.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

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
    public String facultyProfile(Model model,@ModelAttribute("currentUser") Faculty faculty)
    {
        FacultyPojo facultyPojo = new FacultyPojo(faculty.getName(),faculty.getFacultyId(),faculty.getEmail(),faculty.getPassword(),
                faculty.getAge(),faculty.getBirthdate(),faculty.getAddress(),faculty.getCity(),faculty.getState());


        model.addAttribute("PageName","FacultyProfile");
        model.addAttribute("facultyPojo",facultyPojo);

        return "Template";
    }
//<============== Faculty profile page end <===============





//===============> Faculty profile update start ===============>
    @PostMapping("/profile/update")
    public String facultyProfileUpdate(@Validated(OnUpdate.class) @ModelAttribute("facultyPojo")FacultyPojo facultyPojo, BindingResult bindingResult, @ModelAttribute("currentUser") Faculty faculty, Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("PageName", "FacultyProfile");
            return "Template";
        }
        else
        {
            faculty.setName(facultyPojo.getName());
            faculty.setAge(facultyPojo.getAge());
            faculty.setBirthdate(facultyPojo.getBirthdate());
            faculty.setCity(facultyPojo.getCity());
            faculty.setState(facultyPojo.getState());
            faculty.setAddress(facultyPojo.getAddress());

            serviceFacultyDao.saveFaculty(faculty);


            return "redirect:/faculty/profile";
        }
    }
//<============== Faculty profile update end <===============





//===============> Faculty profile pic update start ===============>
    @PostMapping("/profile/updateProfilePic")
    public String updateProfilePic(@RequestParam("profile_img") MultipartFile multipartFile,@ModelAttribute("currentUser") Faculty faculty) throws IOException
    {
        ProfilePic profilePic = faculty.getProfilePic();
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






//===============> Faculty password update page start ===============>
    @GetMapping("/passwordUpdate")
    public String passwordUpdate(Model model)
    {
        model.addAttribute("PageName","FacultyPassUpdate");
        return "Template";

    }
//<============== Faculty password update page end <===============





//===============> Faculty password update  start ===============>
    @PostMapping("/passwordUpdate/process")
    public String processNewPassword(@RequestParam("Password_old") String password_old,@RequestParam("Password_new") String password_new
                                        ,@ModelAttribute("currentUser") Faculty faculty,Model model)
    {
        boolean match = BCrypt.checkpw(password_old,faculty.getPassword());
        if(match)
        {
            if(service1.checkPassValidity(password_new))
            {
                faculty.setPassword(service1.encodePassword(password_new));
                serviceFacultyDao.saveFaculty(faculty);
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
        model.addAttribute("PageName","FacultyPassUpdate");
        return "Template";
    }
//<============== Faculty password update  end <===============






//===============> Faculty update department details page start ===============>
    @GetMapping("/updateDeptdetails")
    public String updateDeptdetails(Model model,@ModelAttribute("currentUser") Faculty faculty)
    {
        FacultyPojo facultyPojo = new FacultyPojo(faculty.getClassTeacher(),faculty.getDesignation(),faculty.getDepartment());
        List<Subject> subjects = serviceSubjectDao.getAllSubjectsOfDepartmentNoFaculty(faculty.getDepartment());

        model.addAttribute("allSubjects",subjects);
        model.addAttribute("PageName","FacultyDeptUpdate");
        model.addAttribute("faculty",facultyPojo);
        return "Template";
    }
//<============== Faculty update department details page end <===============





//===============> Faculty update department details start ===============>
    @PostMapping("/updateDeptdetails/process")
    public String processUpdateDeptDetails(@ModelAttribute("faculty") FacultyPojo facultyPojo,@ModelAttribute("currentUser") Faculty faculty
            ,@RequestParam(value = "subjects", required = false) Set<Integer> subjects)
    {
        faculty.setClassTeacher(facultyPojo.getClassTeacher());
        faculty.setDesignation(facultyPojo.getDesignation());
        faculty.setDepartment(facultyPojo.getDepartment());


        if(faculty.getDesignation().equals("HOD"))
        {
            if(!faculty.getRoles().contains("ROLE_HOD"))
            {
                faculty.addRole("ROLE_HOD");
            }
        }
        else
        {
            if(faculty.getRoles().contains("ROLE_HOD"))
            {
                faculty.getRoles().remove("ROLE_HOD");
            }
        }


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
    public String removeAllocatedSubject(@RequestParam("sId") int id,@ModelAttribute("currentUser") Faculty faculty)
    {
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
    public  String facultySubjects(Model model,@RequestParam("page") int page, @ModelAttribute("currentUser") Faculty faculty)
    {
        Pageable pageable = PageRequest.of(page,5, Sort.by("name"));
        Page<Subject> subjects = serviceSubjectDao.getSubjectsOfFaculty(faculty,pageable);

        model.addAttribute("subjects",subjects);
        model.addAttribute("PageName","FacultySubjects");
        model.addAttribute("pageUrl","faculty/subjects");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",subjects.getTotalPages());
        model.addAttribute("listView","view");
        return "Template";
    }
//<============== Faculty subjects page end <===============





//===============> Faculty subject student list page start ===============>
    @GetMapping("/subjects/list")
    public  String facultySubjectStudentList(Model model,@RequestParam("page") int page, @RequestParam("subject") int id)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);
//        Pageable pageable = PageRequest.of(page,5, Sort.by("name"));
//        Page<Student> students = serviceStudentDao.getAllStudentsOfSubject(id,pageable);
//
        List<Student> students = subject.getStudents();
        model.addAttribute("subjectName",subject);
        model.addAttribute("students",students);
        model.addAttribute("PageName","facultySubjectStudentList");
        model.addAttribute("pageUrl","faculty/subjects/list");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",students.size());

        return "Template";
    }
//<============== Faculty subject student list page end <===============





//===============> Faculty attendance page start ===============>
    @GetMapping("/attendance")
    public  String facultyAttendance(Model model, @RequestParam("page") int page, @ModelAttribute("currentUser") Faculty faculty)
    {
        Pageable pageable = PageRequest.of(page,5, Sort.by("name"));
        Page<Subject> subjects = serviceSubjectDao.getSubjectsOfFaculty(faculty,pageable);

        model.addAttribute("subjects",subjects);
        model.addAttribute("PageName","FacultySubjects");
        model.addAttribute("pageUrl","faculty/attendance");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",subjects.getTotalPages());
        model.addAttribute("attendanceUpdate","attendanceUpdate");
        return "Template";
    }
//<============== Faculty attendance page end <===============





//===============> Faculty attendance view page start ===============>
    @GetMapping("/attendance/view")
    public String viewSubjectAttendance(@RequestParam("subject") Integer id, @RequestParam("page") int page, Model model)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);
        Pageable pageable = PageRequest.of(page,5, Sort.by("id"));

        Page<Attendance> attendances = serviceAttendanceDao.getAttendanceOfSubject(subject,pageable);

        model.addAttribute("attendances",attendances);
        model.addAttribute("subjectName",subject);
        model.addAttribute("subId",id);
        model.addAttribute("pageUrl","faculty/attendance/view");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",attendances.getTotalPages());
        model.addAttribute("PageName","FacultyAttendance");
        return "Template";
    }
//<============== Faculty attendance view page end <===============





//===============> Faculty attendance update page start ===============>
    @GetMapping("/attendance/update")
    public String updateSubjectAttendance(@RequestParam("subject") Integer id, @RequestParam("page") int page,Model model)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);

        Pageable pageable = PageRequest.of(page,5, Sort.by("id"));
        Page<Attendance> attendances = serviceAttendanceDao.getAttendanceOfSubject(subject,pageable);

        model.addAttribute("attendances",attendances);
        model.addAttribute("subjectName",subject);
        model.addAttribute("subId",id);
        model.addAttribute("pageUrl","faculty/attendance/update");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",attendances.getTotalPages());
        model.addAttribute("PageName","FacultyAttendanceUpdate");
        return "Template";
    }
//<============== Faculty attendance update page end <===============





//==============> Faculty attendance update  start ===============>
    @PostMapping("/subjects/attendance/update/process/{page}")
    public String updateSubjectAttendance2(@ModelAttribute("attendance") Attendance attendance,@PathVariable int page)
    {
        Attendance oldAttendance = serviceAttendanceDao.getAttendanceById(attendance.getId());
        oldAttendance.setPresentClasses(attendance.getPresentClasses());
        oldAttendance.setTotalClasses(attendance.getTotalClasses());
        oldAttendance.setUpdatedDate(service1.getTodayDate());

        serviceAttendanceDao.saveAttendance(oldAttendance);
        return "redirect:/faculty/attendance/update?page="+page+"&subject="+ oldAttendance.getSubject().getId();
    }
//<============== Faculty attendance update  end <===============





//===============> Faculty exams  page start ===============>
    @GetMapping("/exams")
    public  String facultyExams(Model model, @RequestParam("page") int page,@ModelAttribute("currentUser") Faculty faculty)
    {
        Pageable pageable = PageRequest.of(page,5, Sort.by("name"));
        Page<Subject> subjects = serviceSubjectDao.getSubjectsOfFaculty(faculty,pageable);

        model.addAttribute("subjects",subjects);
        model.addAttribute("PageName","FacultySubjects");
        model.addAttribute("pageUrl","faculty/attendance");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",subjects.getTotalPages());
        model.addAttribute("studentMarks","studentMarks");
        return "Template";
    }
//<============== Faculty exams  page end <===============





//===============> Faculty exams view page start ===============>
    @GetMapping("/exams/view")
    public String viewStudentMarks(@RequestParam("subject") Integer id ,@RequestParam("page") int page, Model model)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);

        Pageable pageable = PageRequest.of(page,5, Sort.by("id"));
        Page<Score> scores = serviceScoreDao.getScoreForSubject(subject,pageable);

        model.addAttribute("scores",scores);
        model.addAttribute("subjectName",subject);
        model.addAttribute("subId",id);
        model.addAttribute("pageUrl","faculty/exams/view");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",scores.getTotalPages());
        model.addAttribute("PageName","StudentMarksView");
        return "Template";
    }
//<============== Faculty exams view page end <===============





//===============> Faculty exams update page start ===============>
    @GetMapping("/exams/update")
    public String viewStudentUpdate(@RequestParam("subject") Integer id ,@RequestParam("page") int page,Model model)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);

        Pageable pageable = PageRequest.of(page,5, Sort.by("id"));
        Page<Score> scores = serviceScoreDao.getScoreForSubject(subject,pageable);

        model.addAttribute("scores",scores);
        model.addAttribute("subjectName",subject);
        model.addAttribute("subId",id);
        model.addAttribute("pageUrl","faculty/exams/update");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",scores.getTotalPages());
        model.addAttribute("PageName","StudentMarksUpdate");
        return "Template";
    }
//<============== Faculty exams update page end<===============





//===============> Faculty exams update start ===============>
    @PostMapping("/exams/update/process/{page}")
    public String studentScoreUpdate(@ModelAttribute("score") Score score,@PathVariable Integer page)
    {
        Score oldScore = serviceScoreDao.getScoreById(score.getId());
        oldScore.setCt1(score.getCt1());
        oldScore.setCt2(score.getCt2());
        oldScore.setInternal(score.getInternal());
        oldScore.setEndSem(score.getEndSem());

        serviceScoreDao.saveScore(oldScore);
        return "redirect:/faculty/exams/update?page="+page+"&subject="+ oldScore.getSubject().getId();
    }
//<============== Faculty exams update end <===============





//===============> Faculty query view page start ===============>
    @GetMapping("/query")
    public  String facultyQueries(Model model,@ModelAttribute("currentUser") Faculty faculty,@RequestParam("page") int page)
    {
        Pageable pageable = PageRequest.of(page,5,Sort.by("id").descending());
        Page<Query> pendingQueries = serviceQueryDao.getQueriesOfFacultyWithPending(faculty,"Pending",pageable);

        model.addAttribute("queries",pendingQueries);
        model.addAttribute("pageUrl","faculty/query");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",pendingQueries.getTotalPages());
        model.addAttribute("PageName","StudentQueriesView");
        return "Template";
    }
//<============== Faculty query view page end <===============





//===============> Faculty resolved query view page start ===============>
    @GetMapping("/resolved_queries")
    public  String facultyResolvedQueries(Model model,@ModelAttribute("currentUser") Faculty faculty,@RequestParam("page") int page)
    {
        Pageable pageable = PageRequest.of(page,5,Sort.by("id").descending());
        Page<Query> resolvedQueries = serviceQueryDao.getQueriesOfFacultyWithResolved(faculty,"Resolved",pageable);

        model.addAttribute("resolvedQueries",resolvedQueries);
        model.addAttribute("pageUrl","faculty/resolved_queries");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",resolvedQueries.getTotalPages());
        model.addAttribute("PageName","StudentResolvedQueriesView");
        return "Template";
    }
//<============== Faculty resolved query view page end <===============





//===============> Faculty query resolve page start ===============>
    @GetMapping("/query/resolve")
    public String facultyQueryResolve(Model model,@RequestParam("qId") int id,@RequestParam("page") int page)
    {
        Query query = serviceQueryDao.getQueryById(id);
        QueryPojo queryPojo = new QueryPojo();
        queryPojo.setId(query.getId());

        model.addAttribute("query",query);
        model.addAttribute("queryPojo",queryPojo);
        model.addAttribute("page",page);
        model.addAttribute("PageName","FacultyQueryResolve");
        return "Template";
    }
//<============== Faculty query resolve page end <===============





//===============> Faculty query resolve start ===============>
    @PostMapping("/query/resolve/submit/{page}")
    public String facultyQuerySubmit(@ModelAttribute("queryPojo") QueryPojo queryPojo,@PathVariable int page)
    {
        Query query = serviceQueryDao.getQueryById(queryPojo.getId());
        query.setRemark(queryPojo.getRemark());
        query.setResolvedDate(service1.getTodayDate());
        query.setStatus("Resolved");

        serviceQueryDao.saveQuery(query);

        return "redirect:/faculty/query?page="+page;
    }
//<============== Faculty query resolve  end <===============





//===============> Faculty requests view page start ===============>
    @GetMapping("/requests")
    public  String facultyRequests(Model model,@ModelAttribute("currentUser") Faculty faculty,@RequestParam("page") int page)
    {
        Pageable pageable = PageRequest.of(page,5,Sort.by("id").descending());
        Page<SubjectRegistrationRequest>  requests = serviceSubjectRegistrationDao.getRequestsForFaculty(faculty,pageable);

        model.addAttribute("Requests",requests);
        model.addAttribute("pageUrl","faculty/requests");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",requests.getTotalPages());
        model.addAttribute("PageName","FacultyRequests");
        return "Template";
    }
//<============== Faculty requests view page end <===============





//===============> Faculty requests approve start ===============>
    @GetMapping("/requests/approve")
    public String approveSubRegistrationRequest(@RequestParam("stId") int studentId, @RequestParam("subId") int subjectId
                                                    ,@RequestParam("page") int page)
    {
        serviceFaculty.successSubjectRegistration(studentId,subjectId);
        return "redirect:/faculty/requests?page="+page;
    }
//<============== Faculty requests approve start <===============





//===============> Faculty requests reject start ===============>
    @GetMapping("/requests/reject")
    public String rejectSubRegistrationRequest(@RequestParam("stId") int studentId, @RequestParam("subId") int subjectId
            ,@RequestParam("page") int page)
    {
        serviceFaculty.rejectSubjectRegistration(studentId,subjectId);
        return "redirect:/faculty/requests?page="+page;
    }
//<============== Faculty requests reject end <===============





//===============> HOD-Faculty manage subjects page start ===============>
    @PreAuthorize("hasRole('HOD')")
    @GetMapping("/manageSubjects")
    public String manageSubjects(Model model,@ModelAttribute("currentUser") Faculty faculty,@RequestParam("page") int page)
    {
        Pageable pageable = PageRequest.of(page,2,Sort.by("id").descending());
        Page<Subject> subjects = serviceSubjectDao.getAllSubjectsOfDepartment(faculty.getDepartment(),pageable);

        model.addAttribute("subjects",subjects);
        model.addAttribute("pageUrl","faculty/manageSubjects");
        model.addAttribute("pageNo", page);
        model.addAttribute("totalPages",subjects.getTotalPages());
        model.addAttribute("PageName","FacultyManageSubjects");
        return "Template";
    }
//<============== HOD-Faculty manage subjects page end <===============





//===============> HOD-Faculty manage subjects add new page start ===============>
    @PreAuthorize("hasRole('HOD')")
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
    @PreAuthorize("hasRole('HOD')")
    @PostMapping("/manageSubjects/add/newSubject")
    public String addSubject2(@ModelAttribute("subject") SubjectPojo subjectPojo,@ModelAttribute("currentUser") Faculty faculty)
    {
        Subject subject = new Subject(subjectPojo.getName(),subjectPojo.getSubId(),faculty.getDepartment(),
                subjectPojo.getCourse(),subjectPojo.getSemester(), subjectPojo.getCredits());


        serviceSubjectDao.save(subject);

        return "redirect:/faculty/manageSubjects?page=0";
    }
//<============== HOD-Faculty manage subjects add new end <===============




//===============> HOD-Faculty manage subjects edit page start  ===============>
    @PreAuthorize("hasRole('HOD')")
    @GetMapping("/manageSubjects/edit")
    public String editSubject(@RequestParam("sId") int id,Model model,@RequestParam("page") int page)
    {
        Subject subject = serviceSubjectDao.getSubjectById(id);
        SubjectPojo subjectPojo = new SubjectPojo(subject.getName(),subject.getSubId(),subject.getDepartment(),subject.getCourse()
                                        ,subject.getSemester(),subject.getCredits());

        subjectPojo.setId(subject.getId());

        model.addAttribute("subject",subjectPojo);
        model.addAttribute("page",page);
        model.addAttribute("PageName","FacultyEditSubject");
        return "Template";
    }
//<============== HOD-Faculty manage subjects edit page end <===============




//===============> HOD-Faculty manage subjects edit  start  ===============>
    @PreAuthorize("hasRole('HOD')")
    @PostMapping("/manageSubjects/edit/process/{page}")
    public String editSubject2(@PathVariable int page, @ModelAttribute("subject") SubjectPojo subjectPojo)
    {
        Subject oldSubject = serviceSubjectDao.getSubjectById(subjectPojo.getId());

        oldSubject.setName(subjectPojo.getName());
        oldSubject.setSubId(subjectPojo.getSubId());
        oldSubject.setCourse(subjectPojo.getCourse());
        oldSubject.setSemester(subjectPojo.getSemester());
        oldSubject.setCredits(subjectPojo.getCredits());

        serviceSubjectDao.save(oldSubject);


        return "redirect:/faculty/manageSubjects?page="+page;
    }
//<============== HOD-Faculty manage subjects edit  end <===============
}
