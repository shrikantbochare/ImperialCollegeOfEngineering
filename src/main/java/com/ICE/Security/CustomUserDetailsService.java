package com.ICE.Security;

import com.ICE.Entities.Faculty;
import com.ICE.Entities.Student;
import com.ICE.Service.ServiceFacultyDao;
import com.ICE.Service.ServiceStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService implements UserDetailsService {


    private ServiceStudentDao serviceStudentDao;
    private ServiceFacultyDao serviceFacultyDao;


    @Autowired
    public CustomUserDetailsService(ServiceStudentDao serviceStudentDao, ServiceFacultyDao serviceFacultyDao) {
        this.serviceStudentDao = serviceStudentDao;
        this.serviceFacultyDao = serviceFacultyDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = serviceStudentDao.getStudentByUniversityNo(username);
        Faculty faculty = (Faculty) serviceFacultyDao.getFacultyByFacultyId(username);


        if((student == null) && (faculty == null))
        {
            throw new UsernameNotFoundException(username);
        } else if (student == null) {

            UserDetails userDetails = User.withUsername(faculty.getFacultyId())
                    .password(faculty.getPassword())
                    .authorities(grantedAuthorities(faculty.getRoles()))
                    .build();
            return userDetails;

        }else {
            UserDetails userDetails = User.withUsername(student.getUniversityNo())
                    .password(student.getPassword())
                    .authorities(student.getRole())
                    .build();
            return userDetails;

        }

    }


    private Collection<GrantedAuthority> grantedAuthorities(List<String> authorities)
    {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
