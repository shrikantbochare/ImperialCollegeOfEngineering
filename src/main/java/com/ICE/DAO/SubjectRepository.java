package com.ICE.DAO;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

    Page<Subject> findByDepartment(String department, Pageable pageable);

    List<Subject> findByDepartmentAndFacultyIsNull( String department);

    List<Subject> findByIdIn(Set<Integer> Ids);

    List<Subject> findByDepartmentAndCourseAndSemesterAndFacultyIsNotNull(String department,String course,String semester);


    Page<Subject> findByFaculty(Faculty faculty, Pageable pageable);
}
