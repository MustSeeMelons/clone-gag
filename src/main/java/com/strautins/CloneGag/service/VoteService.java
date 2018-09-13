package com.strautins.CloneGag.service;

import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.pojo.PostResponse;
import com.strautins.CloneGag.pojo.VoteResponse;

import java.math.BigInteger;
import java.util.List;

public interface VoteService {
    VoteResponse vote(BigInteger postId, Integer point) throws RestException;
    List<PostResponse> getUpVotedPosts(BigInteger userId, BigInteger page) throws RestException;
}
