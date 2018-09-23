package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.model.PostVote;
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
public class PostVoteDaoImpl implements PostVoteDao {

    private static int PAGE_SIZE = 2;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PostVote loadVote(BigInteger postId, BigInteger userId) {
        String hql = "from PostVote v where v.owner = :userId and v.postId = :postId";
        try {
            return (PostVote) sessionFactory.getCurrentSession().createQuery(hql)
                    .setParameter("userId", userId)
                    .setParameter("postId", postId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void saveVote(PostVote postVote) {
        sessionFactory.getCurrentSession().save(postVote);
    }

    @Override
    public void deleteVote(PostVote postVote) {
        sessionFactory.getCurrentSession().delete(postVote);
    }

    @Override
    public void updateVote(PostVote postVote) {
        sessionFactory.getCurrentSession().update(postVote);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PostResponse> getUpVotes(BigInteger userId, BigInteger page) {
        String hql = "from Post p where p.owner = :userId and p.id " +
                "in (select postId from PostVote v where v.owner = :userId and v.point > 0) order by p.createDate DESC";
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
        }
    }
}
