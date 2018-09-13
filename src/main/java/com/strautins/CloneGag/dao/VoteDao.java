package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.model.Vote;
import com.strautins.CloneGag.pojo.PostResponse;

import java.math.BigInteger;
import java.util.List;

public interface VoteDao {
    Vote loadVote(BigInteger postId, BigInteger userId);

    void saveVote(Vote vote);

    void deleteVote(Vote vote);

    void updateVote(Vote vote);

    List<PostResponse> getUpVotes(BigInteger userId, BigInteger page) throws RestException;
}
