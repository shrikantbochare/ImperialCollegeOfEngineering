package com.ICE.Service;


import com.ICE.DAO.QueryRepository;
import com.ICE.Entities.Faculty;
import com.ICE.Entities.Query;
import com.ICE.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceQueryDaoImpl implements ServiceQueryDao{


    private QueryRepository queryRepository;


    @Autowired
    public ServiceQueryDaoImpl(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }


    @Override
    public void saveQuery(Query query) {
        queryRepository.save(query);
    }


    @Override
    public List<Query> getQueriesOfStudent(Student student) {
        return queryRepository.findByStudent(student);
    }


    @Override
    public Query getQueryById(int id) {
        Optional<Query> value =  queryRepository.findById(id);
        Query query = value.orElseGet(Query::new);
        return query;
    }


    @Override
    public List<Query> getQueriesOfFaculty(Faculty faculty) {
        return queryRepository.findByFaculty(faculty);
    }


    @Override
    public List<Query> getQueriesOfFacultyWithResolved(Faculty faculty, String status) {
        return queryRepository.findByFacultyAndStatus(faculty,"Resolved");
    }

    @Override
    public List<Query> getQueriesOfFacultyWithPending(Faculty faculty, String status) {
        return queryRepository.findByFacultyAndStatus(faculty,"Pending");
    }
}
