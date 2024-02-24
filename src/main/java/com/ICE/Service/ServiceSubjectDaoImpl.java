package com.ICE.Service;


import com.ICE.DAO.SubjectRepository;
import com.ICE.Entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public List<Subject> getAllSubjectsOfDepartment(String department) {
        return subjectRepository.findByDepartment(department);
    }


    @Override
    public List<Subject> getAllSubjectsOfDepartmentNoFaculty(String department) {
        return subjectRepository.findByDepartmentAndFacultyIsNull(department);
    }


    @Override
    public List<Subject> getAllSubjectsWithIds(Set<Integer> Ids) {
        return subjectRepository.findByIdIn(Ids);
    }


    @Override
    public Subject getSubjectById(int id) {
        Optional<Subject> value = subjectRepository.findById(id);
        Subject subject = value.orElseGet(Subject::new);
        return subject;
    }
}
