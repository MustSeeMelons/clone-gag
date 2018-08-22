package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.Post;

import java.math.BigInteger;

public interface PostDao {
    void savePost(Post post);

    Post loadPost(BigInteger id);
}
