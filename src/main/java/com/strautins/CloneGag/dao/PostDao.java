package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.definitions.FeedType;
import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.pojo.PostPage;

import java.math.BigInteger;
import java.util.List;

public interface PostDao {
    void savePost(Post post);

    Post loadPost(BigInteger id);

    void updatePost(Post post);

    PostPage getUserPosts(BigInteger userId, BigInteger page) throws RestException;

    List<Post> getFeed(FeedType type, BigInteger page) throws RestException;
}
