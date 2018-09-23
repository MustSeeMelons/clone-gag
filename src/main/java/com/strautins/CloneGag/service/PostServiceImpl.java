package com.strautins.CloneGag.service;

import com.strautins.CloneGag.dao.PostDao;
import com.strautins.CloneGag.definitions.FeedType;
import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.pojo.PostPage;
import com.strautins.CloneGag.pojo.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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
    public PostPage getUserPosts(BigInteger userId, BigInteger page) {
        return postDao.getUserPosts(userId, page);
    }

    @Override
    public List<PostResponse> getFeed(FeedType type, BigInteger page) {
        return postDao.getFeed(type, page).stream()
                .map((Post post) ->
                        new PostResponse(post)
                ).collect(Collectors.toList());
    }
}
