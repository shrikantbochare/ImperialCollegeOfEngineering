package com.ICE.Service;

import com.ICE.Entities.Subject;

import java.util.List;
import java.util.Set;

public interface ServiceSubjectDao {


    void save (Subject subject);

    List<Subject> getAllSubjectsOfDepartment(String department);


    List<Subject> getAllSubjectsOfDepartmentNoFaculty(String department);


    List<Subject> getAllSubjectsWithIds(Set<Integer> Ids);


    Subject getSubjectById(int id);
}
