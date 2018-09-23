package com.strautins.CloneGag.service;

import com.strautins.CloneGag.definitions.FeedType;
import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.pojo.PostPage;
import com.strautins.CloneGag.pojo.PostResponse;

import java.math.BigInteger;
import java.util.List;

public interface PostService {
    void savePost(Post post);

    Post loadPost(BigInteger id);

    PostPage getUserPosts(BigInteger userId, BigInteger page);

    public List<PostResponse> getFeed(FeedType type, BigInteger page);
}
