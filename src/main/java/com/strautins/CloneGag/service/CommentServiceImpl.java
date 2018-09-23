package com.strautins.CloneGag.service;

import com.strautins.CloneGag.dao.CommentDao;
import com.strautins.CloneGag.model.Comment;
import com.strautins.CloneGag.model.hql.CommentData;
import com.strautins.CloneGag.pojo.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void saveComment(Comment comment) {
        commentDao.saveComment(comment);
    }

    @Override
    public List<CommentResponse> getPostComments(BigInteger postId, BigInteger page) {
        return commentDao.getPostComments(postId, page).stream().map(
                (CommentData commentData) -> new CommentResponse(commentData)
        ).collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getCommentReplies(BigInteger commentId, BigInteger page) {
        return commentDao.getCommentReplies(commentId, page).stream().map(
                (CommentData commentData) -> new CommentResponse(commentData)
        ).collect(Collectors.toList());
    }
}
