package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.Vote;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.math.BigInteger;

@Repository
public class VoteDaoImpl implements VoteDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Vote loadVote(BigInteger postId, BigInteger userId) {
        String hql = "from Vote v where v.owner = :userId and v.postId = :postId";
        try {
            return (Vote) sessionFactory.getCurrentSession().createQuery(hql)
                    .setParameter("userId", userId)
                    .setParameter("postId", postId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void saveVote(Vote vote) {
        sessionFactory.getCurrentSession().save(vote);
    }

    @Override
    public void deleteVote(Vote vote) {
        sessionFactory.getCurrentSession().delete(vote);
    }

    @Override
    public void updateVote(Vote vote) {
        sessionFactory.getCurrentSession().update(vote);
    }
}
