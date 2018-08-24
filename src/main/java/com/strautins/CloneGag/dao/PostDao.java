package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.Post;

import java.math.BigInteger;
import java.util.List;

public interface PostDao {
    void savePost(Post post);

    Post loadPost(BigInteger id);

    List<Post> getUserPosts(BigInteger userId);

    List<Post> getFresh();

    List<Post> getTrending();

    List<Post> getHot();
}
