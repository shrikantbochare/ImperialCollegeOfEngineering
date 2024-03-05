package com.ICE.Service;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.Query;
import com.ICE.Entities.Student;

import java.util.List;

public interface ServiceQueryDao {

    void saveQuery(Query query);

    List<Query> getQueriesOfStudent(Student student);

    Query getQueryById(int id);

    List<Query> getQueriesOfFaculty(Faculty faculty);

    List<Query> getQueriesOfFacultyWithResolved(Faculty faculty,String status);

    List<Query> getQueriesOfFacultyWithPending(Faculty faculty,String status);
}
