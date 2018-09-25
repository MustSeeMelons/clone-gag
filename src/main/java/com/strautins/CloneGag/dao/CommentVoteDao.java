package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.CommentVote;
import java.math.BigInteger;

public interface CommentVoteDao {

    public CommentVote loadVote(BigInteger commentId, BigInteger userId);

    void saveVote(CommentVote commentVote);

    void updateVote(CommentVote commentVote);

    void deleteVote(CommentVote commentVote);
}
