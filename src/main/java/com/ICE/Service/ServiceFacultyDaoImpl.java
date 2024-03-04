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





//===============> Get faculty by Id start ===============>
    @Override
    public Faculty getFacultyById(int id) {
        Optional<Faculty> value = facultyRepository.findById(id);
        Faculty faculty;
        faculty = value.orElseGet(Faculty::new);
        return faculty;
    }
//<==============  Get faculty by Id end <===============






//===============> Save or update faculty start ===============>
    @Override
    public void saveFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }
//<============== Save or update faculty end <===============






//===============> Get faculty - class teacher start ===============>
    @Override
    public Faculty getClassTeacher(String department, String classTeacher) {
        return facultyRepository.findByDepartmentAndClassTeacher(department,classTeacher);
    }
//<============== Get faculty - class teacher end <===============
}
