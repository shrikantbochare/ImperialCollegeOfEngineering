package com.ICE.Service;

import com.ICE.DAO.FacultyRepository;
import com.ICE.Entities.Faculty;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceFacultyDaoImpl implements ServiceFacultyDao{



    private FacultyRepository facultyRepository;


    public ServiceFacultyDaoImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty getFacultyById(int id) {
        Optional<Faculty> value = facultyRepository.findById(id);
        Faculty faculty;
        faculty = value.orElseGet(Faculty::new);
        return faculty;
    }


    @Override
    public void saveFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }


    @Override
    public Faculty getClassTeacher(String department, String classTeacher) {
        return facultyRepository.findByDepartmentAndClassTeacher(department,classTeacher);
    }
}
