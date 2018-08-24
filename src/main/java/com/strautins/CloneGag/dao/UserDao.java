package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.CloneGagUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDao {
    CloneGagUser getByUserName(String username) throws UsernameNotFoundException;

    void saveUser(CloneGagUser user);
}
