package com.ICE.Service;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ServiceSubjectDao {


    void save (Subject subject);

    Page<Subject> getAllSubjectsOfDepartment(String department,Pageable pageable);


    List<Subject> getAllSubjectsOfDepartmentNoFaculty(String department);


    List<Subject> getAllSubjectsWithIds(Set<Integer> Ids);


    Subject getSubjectById(int id);


    List<Subject> getSubjectsForThisSemester(String department,String course,String semester);


    Page<Subject> getSubjectsOfFaculty(Faculty faculty, Pageable pageable);
}
