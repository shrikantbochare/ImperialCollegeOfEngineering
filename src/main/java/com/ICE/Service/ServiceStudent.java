package com.ICE.Service;

import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;

import java.util.List;

public interface ServiceStudent {


    List<Subject> subjectsAvailableForRegistration(Student student);
}
