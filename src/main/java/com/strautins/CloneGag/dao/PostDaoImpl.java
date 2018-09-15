package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.definitions.FeedType;
import com.strautins.CloneGag.exceptions.ExceptionManager;
import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.pojo.PostPage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.awt.image.RescaleOp;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {

    private static int PAGE_SIZE = 5;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void savePost(Post post) {
        sessionFactory.getCurrentSession().save(post);
    }

    @Override
    public Post loadPost(BigInteger id) {
        try {
            return sessionFactory.getCurrentSession().get(Post.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    public void updatePost(Post post) {
        sessionFactory.getCurrentSession().update(post);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PostPage getUserPosts(BigInteger userId, BigInteger page) throws RestException {
        String countHql = "select count(p.id) from Post p";
        String hql = "from Post p where p.owner = :userId order by p.createDate DESC";
        Long count = 0L;
        try {
            count = (Long) sessionFactory.getCurrentSession().createQuery(countHql).uniqueResult();
            List<Post> posts = new ArrayList<>();
            if (count != 0L) {
                posts = sessionFactory.getCurrentSession().createQuery(hql)
                        .setFirstResult(page.intValue() * PAGE_SIZE)
                        .setMaxResults(PAGE_SIZE)
                        .setParameter("userId", userId)
                        .list();
            }

            return new PostPage(posts, page.longValue(), (long) Math.ceil((double) count / PAGE_SIZE));
        } catch (NoResultException e) {
            return new PostPage(new ArrayList<>(), page.longValue(), (long) Math.ceil((double) count / PAGE_SIZE));
        } catch (Exception e) {
            throw ExceptionManager.DBError(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Post> getFeed(FeedType type, BigInteger page) throws RestException {
        String hql = "from Post p order by p.createDate DESC";
        try {
            List<Post> posts = sessionFactory.getCurrentSession().createQuery(hql)
                    .setFirstResult(page.intValue() * PAGE_SIZE)
                    .setMaxResults(PAGE_SIZE)
                    .list();

            if (posts.size() == 0) {
                return new ArrayList<>();
            }

            return posts;
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw ExceptionManager.DBError(e);
        }
    }
}
