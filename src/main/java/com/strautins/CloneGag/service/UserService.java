package com.strautins.CloneGag.service;

import com.strautins.CloneGag.model.CloneGagUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.math.BigInteger;

public interface UserService {
    CloneGagUser getByUserName(String username) throws UsernameNotFoundException;

    BigInteger getCurrentUserId();

    Boolean isLoggedIn();

    void save(CloneGagUser user);

    Boolean isUsernameTaken(String username);
}
