package com.ICE.Service;


import com.ICE.DAO.QueryRepository;
import com.ICE.Entities.Faculty;
import com.ICE.Entities.Query;
import com.ICE.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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




//===============> Save or update query start ===============>
    @Override
    public void saveQuery(Query query) {
        queryRepository.save(query);
    }
//<============== Save or update query end <===============





//===============> Get queries of student start ===============>
    @Override
    public Page<Query> getQueriesOfStudent(Student student, Pageable pageable) {
        return queryRepository.findByStudent(student,pageable);
    }
//<============== Get queries of student  end <===============





//===============> Get query by id start ===============>
    @Override
    public Query getQueryById(int id) {
        Optional<Query> value =  queryRepository.findById(id);
        Query query = value.orElseGet(Query::new);
        return query;
    }
//<============== Get query by id end <===============





//===============> Get queries of faculty start ===============>

    @Override
    public List<Query> getQueriesOfFaculty(Faculty faculty) {
        return queryRepository.findByFaculty(faculty);
    }
//<============== Get queries of faculty  end <===============





//===============> Get resolved queries of faculty  start ===============>

    @Override
    public Page<Query> getQueriesOfFacultyWithResolved(Faculty faculty, String status,Pageable pageable) {
        return queryRepository.findByFacultyAndStatus(faculty,"Resolved",pageable);
    }
//<============== Get resolved queries of faculty  end <===============





//===============> Get pending queries of faculty  start ===============>

    @Override
    public Page<Query> getQueriesOfFacultyWithPending(Faculty faculty, String status,Pageable pageable) {
        return queryRepository.findByFacultyAndStatus(faculty,"Pending",pageable);
    }
//<============== Get pending queries of faculty end <===============

}
