package com.strautins.CloneGag.dao;

import com.strautins.CloneGag.model.Comment;
import com.strautins.CloneGag.model.hql.CommentData;

import java.math.BigInteger;
import java.util.List;

public interface CommentDao {

    Comment loadComment(BigInteger id);

    void saveComment(Comment comment);

    void updateComment(Comment comment);

    List<CommentData> getPostComments(BigInteger postId, BigInteger page);

    List<CommentData> getCommentReplies(BigInteger commentId, BigInteger page);
}
