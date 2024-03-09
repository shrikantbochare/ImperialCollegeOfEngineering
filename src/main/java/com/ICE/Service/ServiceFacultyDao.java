package com.ICE.Service;

import com.ICE.Entities.Faculty;

import java.util.Objects;
import java.util.Optional;

public interface ServiceFacultyDao {

    Faculty getFacultyById(int id);

    void saveFaculty(Faculty faculty);

    Faculty getClassTeacher(String department,String classTeacher);

    Object getFacultyByFacultyId(String facultyId);
}
