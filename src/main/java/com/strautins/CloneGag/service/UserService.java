package com.strautins.CloneGag.service;

import com.strautins.CloneGag.model.CloneGagUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    CloneGagUser getByUserName(String username) throws UsernameNotFoundException;
}
