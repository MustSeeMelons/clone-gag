package com.strautins.CloneGag.service;

import com.strautins.CloneGag.model.Post;

import java.math.BigInteger;

public interface PostService {
    void savePost(Post post);

    Post loadPost(BigInteger id);
}
