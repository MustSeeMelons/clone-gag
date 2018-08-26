package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.CloneGagUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CloneGagUser getByUserName(String username) throws UsernameNotFoundException {
        CloneGagUser user = (CloneGagUser) sessionFactory.getCurrentSession()
                .createQuery("from CloneGagUser c where c.username =:username")
                .setParameter("username", username).getSingleResult();

        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("Could not find user by username: " + username);
    }

    @Override
    public void saveUser(CloneGagUser user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public Boolean isUsernameTaken(String username) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from CloneGagUser c where c.username =:username")
                    .setParameter("username", username).getSingleResult() != null;
        } catch (NoResultException e) {
            return false;
        }
    }
}
