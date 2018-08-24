package com.strautins.CloneGag.service;

import com.strautins.CloneGag.dao.PostDao;
import com.strautins.CloneGag.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Override
    public void savePost(Post post) {
        postDao.savePost(post);
    }

    @Override
    public Post loadPost(BigInteger id) {
        return postDao.loadPost(id);
    }

    @Override
    public List<Post> getUserPosts(BigInteger userId) {
        return postDao.getUserPosts(userId);
    }

    @Override
    public List<Post> getFresh() {
        return postDao.getFresh();
    }

    @Override
    public List<Post> getTrending() {
        return postDao.getTrending();
    }

    @Override
    public List<Post> getHot() {
        return postDao.getHot();
    }
}
