package com.ICE.DAO;

import com.ICE.Entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

    List<Subject> findByDepartment(String department);

    List<Subject> findByDepartmentAndFacultyIsNull( String department);

    List<Subject> findByIdIn(Set<Integer> Ids);
}
