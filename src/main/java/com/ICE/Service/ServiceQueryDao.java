package com.ICE.Service;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.Query;
import com.ICE.Entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceQueryDao {

    void saveQuery(Query query);

    Page<Query> getQueriesOfStudent(Student student,Pageable pageable);

    Query getQueryById(int id);

    List<Query> getQueriesOfFaculty(Faculty faculty);

    Page<Query> getQueriesOfFacultyWithResolved(Faculty faculty, String status, Pageable pageable);

    Page<Query> getQueriesOfFacultyWithPending(Faculty faculty, String status, Pageable pageable);
}
