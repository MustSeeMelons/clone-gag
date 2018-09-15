package com.strautins.CloneGag.controllers;

import com.strautins.CloneGag.definitions.FeedType;
import com.strautins.CloneGag.exceptions.ExceptionManager;
import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.pojo.PostPage;
import com.strautins.CloneGag.pojo.PostResponse;
import com.strautins.CloneGag.pojo.VoteResponse;
import com.strautins.CloneGag.service.PostService;
import com.strautins.CloneGag.service.UserService;
import com.strautins.CloneGag.service.VoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * REST controller for handling operation on posts.
 */
@RestController
@RequestMapping("/v1/post")
public class PostRestController {

    private static final Logger LOG = LogManager.getLogger(PostRestController.class.getName());

    @Autowired
    private PostService postService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    /**
     * REST endpoint for getting user posts.
     *
     * @return
     * @throws RestException
     */
    @RequestMapping(value = "/{userId}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public PostPage getUserPostPage(
            @PathVariable(value = "userId", required = false) BigInteger userId,
            @PathVariable("page") BigInteger page
    ) throws RestException {
        try {
            return postService.getUserPosts(userId, page);
        } catch (Exception e) {
            throw ExceptionManager.InternalError(e);
        }
    }

    /**
     * REST endpoint for getting posts of a feed.
     *
     * @param type
     * @return
     * @throws RestException
     */
    @RequestMapping(value = "/feed/{type}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<PostResponse> getPostFeedPage(
            @PathVariable("type") FeedType type,
            @PathVariable("page") BigInteger page
    ) throws RestException {
        try {
            return postService.getFeed(type, page);
        } catch (Exception e) {
            throw ExceptionManager.InternalError(e);
        }
    }

    /**
     * REST endpoint for getting user up voted posts.
     *
     * @param userId
     * @param page
     * @return
     * @throws RestException
     */
    @RequestMapping(value = "/votes/{userId}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<PostResponse> getUpVotedPostPage(
            @PathVariable(value = "userId", required = false) BigInteger userId,
            @PathVariable(value = "page", required = false) BigInteger page
    ) throws RestException {
        try {
            if (userId == null) {
                userId = userService.getCurrentUserId();
            }
            return voteService.getUpVotedPosts(userId, page);
        } catch (Exception e) {
            throw ExceptionManager.InternalError(e);
        }
    }

    /**
     * REST endpoint for voting on posts.
     *
     * @param postId
     * @param point
     * @return
     * @throws RestException
     */
    @RequestMapping(value = "/vote/{id}/{point}", method = RequestMethod.GET)
    @ResponseBody
    public VoteResponse vote(
            @PathVariable(value = "id") BigInteger postId,
            @PathVariable(value = "point") Integer point) throws RestException {
        try {
            return voteService.vote(postId, point);
        } catch (Exception e) {
            throw ExceptionManager.InternalError(e);
        }
    }
}
