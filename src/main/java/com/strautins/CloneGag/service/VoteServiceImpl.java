package com.strautins.CloneGag.service;

import com.strautins.CloneGag.dao.PostDao;
import com.strautins.CloneGag.dao.PostVoteDao;
import com.strautins.CloneGag.exceptions.ExceptionManager;
import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.model.PostVote;
import com.strautins.CloneGag.pojo.PostResponse;
import com.strautins.CloneGag.pojo.PostVoteResponse;
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
    private PostDao postDao;

    @Autowired
    private UserService userService;

    @Override
    public PostVoteResponse vote(BigInteger postId, Integer point) {
        BigInteger userId = userService.getCurrentUserId();
        if (userId == null) {
//            ExceptionManager.NoUserException();
        }

        PostVote postVote = postVoteDao.loadVote(postId, userId);
        Post post = postDao.loadPost(postId);

        if (post == null) {
//            ExceptionManager.PostNotFoundException();
        }

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

        return new PostVoteResponse(postId, post.getPoints());
    }

    @Override
    public List<PostResponse> getUpVotedPosts(BigInteger userId, BigInteger page) {
        List<PostResponse> posts = postVoteDao.getUpVotes(userId, page);

        LOG.debug(posts);

        return posts;
    }


}
