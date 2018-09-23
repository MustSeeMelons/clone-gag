package com.strautins.CloneGag.service;

import com.strautins.CloneGag.pojo.PostResponse;
import com.strautins.CloneGag.pojo.PostVoteResponse;

import java.math.BigInteger;
import java.util.List;

public interface VoteService {
    PostVoteResponse vote(BigInteger postId, Integer point);

    List<PostResponse> getUpVotedPosts(BigInteger userId, BigInteger page);
}
