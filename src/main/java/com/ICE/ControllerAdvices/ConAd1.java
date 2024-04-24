package com.ICE.ControllerAdvices;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.thymeleaf.exceptions.TemplateInputException;

import java.io.IOException;

@ControllerAdvice
public class ConAd1 {







// =============> Exception handler for failed Mail =================>
//    @org.springframework.web.bind.annotation.ExceptionHandler(MessagingException.class)
//    public String mailException(MessagingException e, Model model)
//    {
//        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.SERVICE_UNAVAILABLE);
//
//        problemDetail.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
//        problemDetail.setDetail("Message Not Sent");
//
//        model.addAttribute("mailNotSent",problemDetail);
//        return "redirect:/registerEmail";
//
//    }
// <============ Exception handler for failed Mail <================






// =============> Exception handler IO Exception =================>
    @org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
    public String IoException(IOException e,Model model)
    {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail("Bad Request");
        problemDetail.setStatus(HttpStatus.BAD_REQUEST);

        model.addAttribute("PageName","Exception");
        return "Template";
    }
// <============= Exception handler IO Exception <=================






 //=============> Exception handler global Exception =================>
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String parentException(Exception e,Model model)
    {
        model.addAttribute("PageName","Exception");
        return "Template";
    }
// <============= Exception handler global Exception <=================





// =============> Exception handler TemplateInputException  =================>
    @org.springframework.web.bind.annotation.ExceptionHandler(TemplateInputException.class)
    public String templateInputExceptionHandler(TemplateInputException t,Model model)
    {
        model.addAttribute("PageName","Exception");
        return "Template";
    }
// <============= Exception handler TemplateInputException <=================
//TemplateProcessingException
}
