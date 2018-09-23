package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.PostVote;
import com.strautins.CloneGag.pojo.PostResponse;

import java.math.BigInteger;
import java.util.List;

public interface PostVoteDao {
    PostVote loadVote(BigInteger postId, BigInteger userId);

    void saveVote(PostVote postVote);

    void deleteVote(PostVote postVote);

    void updateVote(PostVote postVote);

    List<PostResponse> getUpVotes(BigInteger userId, BigInteger page);
}
