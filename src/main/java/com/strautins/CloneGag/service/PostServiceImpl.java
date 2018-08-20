package com.strautins.CloneGag.service;

import com.strautins.CloneGag.dao.PostDao;
import com.strautins.CloneGag.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Override
    public void savePost(Post post) {
        postDao.savePost(post);
    }
}
