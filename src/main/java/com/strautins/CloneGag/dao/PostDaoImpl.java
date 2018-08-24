package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void savePost(Post post) {
        sessionFactory.getCurrentSession().save(post);
    }

    @Override
    public Post loadPost(BigInteger id) {
        return sessionFactory.getCurrentSession().get(Post.class, id);
    }

    // TODO pagination for this
    @Override
    public List<Post> getUserPosts(BigInteger userId) {
        String hql = "from Post p where p.owner = :userId order by p.createDate";
        return sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("userId", userId)
                .list();
    }

    @Override
    public List<Post> getFresh() {
        String hql = "from Post p order by p.createDate";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Post> getTrending() {
        // TODO need up vote functionality
        return null;
    }

    @Override
    public List<Post> getHot() {
        // TODO need up vote functionality
        return null;
    }
}
