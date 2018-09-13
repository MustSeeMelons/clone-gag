package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.Vote;

import java.math.BigInteger;

public interface VoteDao {
    Vote loadVote(BigInteger postId, BigInteger userId);

    void saveVote(Vote vote);

    void deleteVote(Vote vote);

    void updateVote(Vote vote);
}
