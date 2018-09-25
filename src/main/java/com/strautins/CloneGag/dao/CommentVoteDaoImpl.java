package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.CommentVote;
import java.math.BigInteger;
import javax.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentVoteDaoImpl implements CommentVoteDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CommentVote loadVote(BigInteger commentId, BigInteger userId) {
        String hql = "from CommentVote v where v.owner = :userId and v.commentId = :commentId";
        try {
            return (CommentVote) sessionFactory.getCurrentSession().createQuery(hql)
                    .setParameter("userId", userId)
                    .setParameter("commentId", commentId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void saveVote(CommentVote commentVote) {
        sessionFactory.getCurrentSession().save(commentVote);
    }

    @Override
    public void updateVote(CommentVote commentVote) {
        sessionFactory.getCurrentSession().update(commentVote);
    }

    @Override
    public void deleteVote(CommentVote commentVote) {
        sessionFactory.getCurrentSession().delete(commentVote);
    }

}
