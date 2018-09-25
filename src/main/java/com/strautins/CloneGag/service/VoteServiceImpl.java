package com.strautins.CloneGag.service;

import com.strautins.CloneGag.dao.CommentDao;
import com.strautins.CloneGag.dao.CommentVoteDao;
import com.strautins.CloneGag.dao.PostDao;
import com.strautins.CloneGag.dao.PostVoteDao;
import com.strautins.CloneGag.model.Comment;
import com.strautins.CloneGag.model.CommentVote;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.model.PostVote;
import com.strautins.CloneGag.pojo.PostResponse;
import com.strautins.CloneGag.pojo.VoteResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service("voteService")
@Transactional
public class VoteServiceImpl implements VoteService {

    private static final Logger LOG = LogManager.getLogger(VoteServiceImpl.class.getName());

    @Autowired
    private PostVoteDao postVoteDao;

    @Autowired
    private CommentVoteDao commentVoteDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserService userService;

    @Override
    public VoteResponse voteOnPost(BigInteger postId, Integer point) {
        BigInteger userId = userService.getCurrentUserId();

        PostVote postVote = postVoteDao.loadVote(postId, userId);
        Post post = postDao.loadPost(postId);

        // If we don't have a postVote, simply create one with the appropriate points
        if (postVote == null) {
            PostVote newPostVote = new PostVote();
            newPostVote.setOwner(userId);
            newPostVote.setPostId(postId);
            newPostVote.setPoint(point);

            postVoteDao.saveVote(newPostVote);
            post.modifyPoints(point);
        } else {
            // Undo postVote
            if (postVote.getPoint() == point) {
                postVoteDao.deleteVote(postVote);
                post.modifyPoints(-point);
            } else {
                // Change postVote type
                post.modifyPoints(-postVote.getPoint() * 2);
                postVote.setPoint(point);
                postVoteDao.updateVote(postVote);
            }
        }

        post.setModifiedDate(new Date());
        postDao.updatePost(post);

        return new VoteResponse(postId, post.getPoints());
    }

    @Override
    public List<PostResponse> getUpVotedPosts(BigInteger userId, BigInteger page) {
        List<PostResponse> posts = postVoteDao.getUpVotes(userId, page);

        LOG.debug(posts);

        return posts;
    }

    @Override
    public VoteResponse voteOnComment(BigInteger commentId, Integer point) {
        BigInteger userId = userService.getCurrentUserId();

        Comment comment = commentDao.loadComment(commentId);
        CommentVote vote = commentVoteDao.loadVote(commentId, userId);
        if (vote == null) {
            CommentVote newVote = new CommentVote();
            newVote.setOwner(userId);
            newVote.setPoint(point);
            newVote.setCommentId(commentId);

            commentVoteDao.saveVote(newVote);
            comment.modifyPoints(point);
        } else {
            // Undo postVote
            if (vote.getPoint() == point) {
                commentVoteDao.deleteVote(vote);
                comment.modifyPoints(-point);
            } else {
                // Change postVote type
                comment.modifyPoints(-vote.getPoint() * 2);
                vote.setPoint(point);
                commentVoteDao.updateVote(vote);
            }
        }

        commentDao.updateComment(comment);

        return new VoteResponse(commentId, comment.getPoints());
    }

}
