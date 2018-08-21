package com.strautins.CloneGag.security;

import com.strautins.CloneGag.model.CloneGagUser;
import com.strautins.CloneGag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CloneGagUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public CloneGagUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CloneGagUser user = userService.getByUserName(username);
        return user.getUserDetails();
    }

}
