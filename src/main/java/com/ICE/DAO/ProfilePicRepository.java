package com.ICE.DAO;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.ProfilePic;
import com.ICE.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePicRepository extends JpaRepository<ProfilePic,Integer> {

    ProfilePic findByStudent(Student student);

    ProfilePic findByFaculty(Faculty faculty);
}
