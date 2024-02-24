package com.ICE.Service;


import com.ICE.Entities.ProfilePic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class Service1Impl implements Service1{


    private ServiceProfilePicDao serviceProfilePicDao;


    @Autowired
    public Service1Impl(ServiceProfilePicDao serviceProfilePicDao) {
        this.serviceProfilePicDao = serviceProfilePicDao;
    }

    @Override
    public void profilePicUpdate(ProfilePic profilePic, MultipartFile multipartFile) throws IOException {
        File file = new ClassPathResource("static/Images/ProfilePic").getFile();

        Path path = Paths.get(file.getAbsolutePath()+File.separator+multipartFile.getOriginalFilename());
        profilePic.setPic(multipartFile.getOriginalFilename());
        serviceProfilePicDao.saveProfilePic(profilePic);
        multipartFile.transferTo(path);

    }


    @Override
    public void deleteProfilePicFromPath(String picName) throws IOException{
        File thePath = new ClassPathResource("static/Images/ProfilePic").getFile();
        Path path = Paths.get(thePath.getAbsolutePath()+File.separator+picName);

        File file = new File(path.toString());

        if(file.exists())
        {
            boolean result = file.delete();
        }
    }
}
