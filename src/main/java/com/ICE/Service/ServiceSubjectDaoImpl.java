package com.ICE.Service;


import com.ICE.DAO.SubjectRepository;
import com.ICE.Entities.Faculty;
import com.ICE.Entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ServiceSubjectDaoImpl implements ServiceSubjectDao{


    private SubjectRepository subjectRepository;


    @Autowired
    public ServiceSubjectDaoImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }




//===============> Get all subjects of a department start ===============>
    @Override
    public Page<Subject> getAllSubjectsOfDepartment(String department, Pageable pageable) {
        return subjectRepository.findByDepartment(department,pageable);
    }
//<============== Get all subjects of a department end <===============





//===============> Get all unallocated subjects of a department start ===============>
    @Override
    public List<Subject> getAllSubjectsOfDepartmentNoFaculty(String department) {
        return subjectRepository.findByDepartmentAndFacultyIsNull(department);
    }
//<============== Get all unallocated subjects of a department end <===============





//===============> Get all subjects with Ids start ===============>
    @Override
    public List<Subject> getAllSubjectsWithIds(Set<Integer> Ids) {
        return subjectRepository.findByIdIn(Ids);
    }
//<============== Get all subjects with Ids end <===============





//===============> Get subject by Id start ===============>
    @Override
    public Subject getSubjectById(int id) {
        Optional<Subject> value = subjectRepository.findById(id);
        Subject subject = value.orElseGet(Subject::new);
        return subject;
    }
//<============== Get subject by Id end <===============





//===============> Get subjects available for this semester start ===============>
    @Override
    public List<Subject> getSubjectsForThisSemester(String department, String course, String semester) {
        return subjectRepository.findByDepartmentAndCourseAndSemesterAndFacultyIsNotNull(department, course, semester);
    }
//<============== Get subjects available for this semester end <===============





//===============> Get all subjects allocated to a faculty start ===============>
    @Override
    public Page<Subject> getSubjectsOfFaculty(Faculty faculty, Pageable pageable) {
        return subjectRepository.findByFaculty(faculty,pageable);
    }
//<============== Get all subjects allocated to a faculty end <===============
}
