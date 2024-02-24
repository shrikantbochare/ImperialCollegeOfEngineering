package com.ICE.Service;

import com.ICE.Entities.ProfilePic;

public interface ServiceProfilePicDao {

    void saveProfilePic(ProfilePic profilePic);

    ProfilePic getProfilePicById(int id);
}
