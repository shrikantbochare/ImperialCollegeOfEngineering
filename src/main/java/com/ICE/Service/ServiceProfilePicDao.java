package com.ICE.Service;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.ProfilePic;
import com.ICE.Entities.Student;

public interface ServiceProfilePicDao {

    void saveProfilePic(ProfilePic profilePic);

    ProfilePic getProfilePicById(int id);

    ProfilePic getProfilePicOfStudent(Student student);

    ProfilePic getProfilePicOfFaculty(Faculty faculty);
}
