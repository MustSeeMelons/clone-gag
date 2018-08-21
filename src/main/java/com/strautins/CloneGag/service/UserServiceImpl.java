package com.strautins.CloneGag.service;

import com.strautins.CloneGag.dao.UserDao;
import com.strautins.CloneGag.model.CloneGagUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public CloneGagUser getByUserName(String username) throws UsernameNotFoundException {
        return userDao.getByUserName(username);
    }
}
