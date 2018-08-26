package com.strautins.CloneGag.service;

import com.strautins.CloneGag.dao.UserDao;
import com.strautins.CloneGag.definitions.Roles;
import com.strautins.CloneGag.model.CloneGagUser;
import com.strautins.CloneGag.model.UserRole;
import com.strautins.CloneGag.security.CloneGagUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public CloneGagUser getByUserName(String username) throws UsernameNotFoundException {
        return userDao.getByUserName(username);
    }

    /**
     * Returns the id of the current user or null.
     *
     * @return
     */
    @Override
    public BigInteger getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof CloneGagUserDetails) {
            return ((CloneGagUserDetails) principal).getId();
        }
        return null;
    }

    @Override
    public Boolean isLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal() instanceof CloneGagUserDetails;
    }

    @Override
    public void save(CloneGagUser user) {
        UserRole role = new UserRole(Roles.USER);
        role.setUsername(user.getUsername());
        user.setEnabled(true);
        user.addRole(role);
        userDao.saveUser(user);
    }

    @Override
    public Boolean isUsernameTaken(String username) {
        return userDao.isUsernameTaken(username);
    }
}
