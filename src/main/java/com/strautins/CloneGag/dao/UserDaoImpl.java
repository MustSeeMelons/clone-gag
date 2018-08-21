package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.CloneGagUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    // TODO So so ugly.. time for iBatis?
    @Override
    public CloneGagUser getByUserName(String username) throws UsernameNotFoundException {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<CloneGagUser> criteriaQuery = builder.createQuery(CloneGagUser.class);
        Root<CloneGagUser> root = criteriaQuery.from(CloneGagUser.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("username"), username));

        CloneGagUser user = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getSingleResult();

        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("Could not find user by username: " + username);
    }
}
