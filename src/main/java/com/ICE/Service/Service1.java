package com.ICE.Service;

import com.ICE.Entities.ProfilePic;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;

public interface Service1 {

    void profilePicUpdate(ProfilePic profilePic, MultipartFile file) throws IOException;

    void deleteProfilePicFromPath(String picName) throws IOException;

    Date getTodayDate();
}
