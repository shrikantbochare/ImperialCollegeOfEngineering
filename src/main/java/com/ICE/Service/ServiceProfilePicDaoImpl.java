package com.ICE.Service;


import com.ICE.DAO.ProfilePicRepository;
import com.ICE.Entities.Faculty;
import com.ICE.Entities.ProfilePic;
import com.ICE.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceProfilePicDaoImpl implements ServiceProfilePicDao{


    private ProfilePicRepository profilePicRepository;



    @Autowired
    public ServiceProfilePicDaoImpl(ProfilePicRepository profilePicRepository) {
        this.profilePicRepository = profilePicRepository;
    }


//===============> Save or update profile pic start ===============>
    @Override
    public void saveProfilePic(ProfilePic profilePic) {
        profilePicRepository.save(profilePic);
    }
//<============== Save or update profile pic end <===============





//===============> Get the profile pic by Id start ===============>
    @Override
    public ProfilePic getProfilePicById(int id)
    {
        Optional<ProfilePic> value = profilePicRepository.findById(id);
        ProfilePic profilePic;
        profilePic = value.orElseGet(ProfilePic::new);
        return profilePic;
    }
//<============== Get the profile pic by Id end <===============





//===============> Get the profile pic of student start ===============>
    @Override
    public ProfilePic getProfilePicOfStudent(Student student) {
        return profilePicRepository.findByStudent(student);
    }
//<============== Get the profile pic of student end <===============





//===============> Get the profile pic of faculty start ===============>
    @Override
    public ProfilePic getProfilePicOfFaculty(Faculty faculty) {
        return profilePicRepository.findByFaculty(faculty);
    }
//<============== Get the profile pic of faculty end <===============
}
