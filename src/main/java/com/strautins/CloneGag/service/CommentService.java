package com.strautins.CloneGag.service;

import com.strautins.CloneGag.model.Comment;
import com.strautins.CloneGag.pojo.CommentResponse;

import java.math.BigInteger;
import java.util.List;

public interface CommentService {
    void saveComment(Comment comment);

    List<CommentResponse> getPostComments(BigInteger postId, BigInteger page);

    List<CommentResponse> getCommentReplies(BigInteger commentId, BigInteger page);
}
