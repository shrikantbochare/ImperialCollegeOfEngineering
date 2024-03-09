package com.ICE.DAO;

import com.ICE.Entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository  extends JpaRepository<Faculty,Integer> {

    Faculty findByDepartmentAndClassTeacher(String department, String classTeacher);

    Optional<Faculty> findByFacultyId(String facultyId);
}
