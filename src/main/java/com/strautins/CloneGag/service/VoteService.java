package com.strautins.CloneGag.service;

import com.strautins.CloneGag.pojo.PostResponse;
import com.strautins.CloneGag.pojo.VoteResponse;

import java.math.BigInteger;
import java.util.List;

public interface VoteService {
    VoteResponse voteOnPost(BigInteger postId, Integer point);
    
    VoteResponse voteOnComment(BigInteger commentId, Integer point);
    
    List<PostResponse> getUpVotedPosts(BigInteger userId, BigInteger page);
}
