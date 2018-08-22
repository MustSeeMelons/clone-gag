package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

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
}
