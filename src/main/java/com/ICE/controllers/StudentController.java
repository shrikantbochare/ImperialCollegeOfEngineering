package com.ICE.controllers;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/profile")
    public String profile(Model model)
    {
        model.addAttribute("PageName","StudentProfile");
        return "Template";
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
