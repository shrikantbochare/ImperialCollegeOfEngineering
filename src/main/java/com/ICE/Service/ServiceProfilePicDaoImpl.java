package com.ICE.Service;


import com.ICE.DAO.ProfilePicRepository;
import com.ICE.Entities.ProfilePic;
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
}
