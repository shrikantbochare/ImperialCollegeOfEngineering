package com.ICE.DAO;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.Student;
import com.ICE.Entities.Subject;
import com.ICE.Entities.SubjectRegistrationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRegistrationRepository  extends JpaRepository<SubjectRegistrationRequest,Integer> {


    @Query("Select r from SubjectRegistrationRequest r where r.status='Pending' And r.subject.faculty=:data")
    Page<SubjectRegistrationRequest> findByFaculty(@Param("data")Faculty faculty, Pageable pageable);


    void deleteByStudentAndSubject(Student student, Subject Subject);


    SubjectRegistrationRequest findByStudentAndSubject(Student student,Subject subject);


    @Query("Select r from SubjectRegistrationRequest r where r.student=:data and r.status !='Rejected'")
    List<SubjectRegistrationRequest> findByStudentWithNotRejected(@Param("data") Student student);



    List<SubjectRegistrationRequest> findByStudent(@Param("data") Student student);

}
