package com.ICE.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faculty")
public class FacultyController {



    @GetMapping("/profile")
    public String facultyProfile(Model model)
    {
        model.addAttribute("PageName","FacultyProfile");
        return "Template";
    }



    @GetMapping("/updateDeptdetails")
    public String updateDeptdetails(Model model)
    {
        model.addAttribute("PageName","FacultyDeptUpdate");
        return "Template";
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
}
