package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.exceptions.ExceptionManager;
import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.model.Vote;
import com.strautins.CloneGag.pojo.PostResponse;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VoteDaoImpl implements VoteDao {

    private static int PAGE_SIZE = 2;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<PostResponse> getUpVotes(BigInteger userId, BigInteger page) throws RestException {
        String hql = "from Post p where p.owner = :userId and p.id " +
                "in (select postId from Vote v where v.owner = :userId and v.point > 0) order by p.createDate DESC";
        try {
            List<Post> posts = (List<Post>) sessionFactory.getCurrentSession().createQuery(hql)
                    .setParameter("userId", userId)
                    .setFirstResult(page.intValue() * PAGE_SIZE)
                    .setMaxResults(PAGE_SIZE)
                    .list();

            if (posts.size() == 0) {
                return new ArrayList<>();
            }

            return posts.stream().map((Post post) ->
                    new PostResponse(post)
            ).collect(Collectors.toList());
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw ExceptionManager.DBError(e);
        }
    }
}
