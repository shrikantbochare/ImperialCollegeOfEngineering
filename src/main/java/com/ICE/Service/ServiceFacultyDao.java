package com.ICE.Service;

import com.ICE.Entities.Faculty;

public interface ServiceFacultyDao {

    Faculty getFacultyById(int id);

    void saveFaculty(Faculty faculty);
}
