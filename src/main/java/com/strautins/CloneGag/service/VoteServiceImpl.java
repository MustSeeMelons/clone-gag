package com.strautins.CloneGag.service;

import com.strautins.CloneGag.dao.PostDao;
import com.strautins.CloneGag.dao.VoteDao;
import com.strautins.CloneGag.exceptions.ExceptionManager;
import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.model.Vote;
import com.strautins.CloneGag.pojo.VoteResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;

@Service("voteService")
@Transactional
public class VoteServiceImpl implements VoteService {

    private static final Logger LOG = LogManager.getLogger(VoteServiceImpl.class.getName());

    @Autowired
    private VoteDao voteDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserService userService;

    @Override
    public VoteResponse vote(BigInteger postId, Integer point) throws RestException {
        BigInteger userId = userService.getCurrentUserId();
        if (userId == null) {
            ExceptionManager.NoUserException();
        }
        Vote vote = voteDao.loadVote(postId, userId);
        Post post = postDao.loadPost(postId);

        if (post == null) {
            ExceptionManager.PostNotFoundException();
        }

        // If we don't have a vote, simply create one with the appropriate points
        if (vote == null) {
            Vote newVote = new Vote();
            newVote.setOwner(userId);
            newVote.setPostId(postId);
            newVote.setPoint(point);

            voteDao.saveVote(newVote);
            post.modifyPoints(point);
        } else {
            // Undo vote
            if (vote.getPoint() == point) {
                voteDao.deleteVote(vote);
                post.modifyPoints(-point);
            } else {
                // Change vote type
                post.modifyPoints(-vote.getPoint() * 2);
                vote.setPoint(point);
                voteDao.updateVote(vote);
            }
        }

        post.setModifiedDate(new Date());
        postDao.updatePost(post);

        return new VoteResponse(postId, post.getPoints());
    }
}
