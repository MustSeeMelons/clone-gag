package com.strautins.CloneGag.controllers;

import com.strautins.CloneGag.definitions.FeedType;
import com.strautins.CloneGag.exceptions.ExceptionManager;
import com.strautins.CloneGag.model.Comment;
import com.strautins.CloneGag.pojo.*;
import com.strautins.CloneGag.service.CommentService;
import com.strautins.CloneGag.service.PostService;
import com.strautins.CloneGag.service.UserService;
import com.strautins.CloneGag.service.VoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Date;
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

    @Autowired
    private CommentService commentService;

    @ExceptionHandler
    public ErrorResponse handleExceptions(Exception e) {
        LOG.error(e);
        return new ErrorResponse("Internal error.", ExceptionManager.ProcessCodes.INTERNAL_ERROR.getCode());
    }

    /**
     * REST endpoint for getting user posts.
     *
     * @return
     */
    @RequestMapping(value = "/{userId}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public PostPage getUserPostPage(
            @PathVariable(value = "userId", required = false) BigInteger userId,
            @PathVariable("page") BigInteger page
    ) {
        return postService.getUserPosts(userId, page);
    }

    /**
     * REST endpoint for getting posts of a feed.
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/feed/{type}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<PostResponse> getPostFeedPage(
            @PathVariable("type") FeedType type,
            @PathVariable("page") BigInteger page
    ) {
        return postService.getFeed(type, page);
    }

    /**
     * REST endpoint for getting user up voted posts.
     *
     * @param userId
     * @param page
     * @return
     */
    @RequestMapping(value = "/votes/{userId}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<PostResponse> getUpVotedPostPage(
            @PathVariable(value = "userId", required = false) BigInteger userId,
            @PathVariable(value = "page", required = false) BigInteger page
    ) {
        if (userId == null) {
            userId = userService.getCurrentUserId();
        }
        return voteService.getUpVotedPosts(userId, page);
    }

    /**
     * REST endpoint for voting on posts.
     *
     * @param postId
     * @param point
     * @return
     */
    @RequestMapping(value = "/vote/{id}/{point}", method = RequestMethod.GET)
    @ResponseBody
    public VoteResponse vote(
            @PathVariable(value = "id") BigInteger postId,
            @PathVariable(value = "point") Integer point) {
        return voteService.voteOnPost(postId, point);
    }

    /**
     * REST endpoint for voting on comments.
     *
     * @param postId
     * @param point
     * @return
     */
    @RequestMapping(value = "/comment/vote/{id}/{point}", method = RequestMethod.GET)
    @ResponseBody
    public VoteResponse commentVote(
            @PathVariable(value = "id") BigInteger comemntId,
            @PathVariable(value = "point") Integer point) {
        return voteService.voteOnComment(comemntId, point);
    }

    /**
     * REST endpoint for commenting on posts and replying to comments.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    @ResponseBody
    public ProcessResponse addComment(@RequestBody @Valid AddCommentRequest request) {
        BigInteger userId = userService.getCurrentUserId();

        Comment comment = new Comment();
        comment.setOwner(userId);
        comment.setValue(request.getComment());
        comment.setPostId(request.getPostId());
        comment.setParentCommentId(request.getCommentId());
        comment.setCreateDate(new Date());

        commentService.saveComment(comment);

        return new ProcessResponse(ExceptionManager.ProcessCodes.OK.getCode(), comment);
    }

    /**
     * REST endpoint for getting top level comments for a post.
     *
     * @param postId
     * @return
     */
    @RequestMapping(value = "comment/get/{postId}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<CommentResponse> getPostComments(
            @PathVariable(value = "postId") BigInteger postId,
            @PathVariable(value = "page") BigInteger page
    ) {
        return commentService.getPostComments(postId, page);
    }

    @RequestMapping(value = "comment/replies/{commentId}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<CommentResponse> getCommentReplies(
            @PathVariable(value = "commentId") BigInteger commentId,
            @PathVariable(value = "page") BigInteger page
    ) {
        return commentService.getCommentReplies(commentId, page);
    }
}
