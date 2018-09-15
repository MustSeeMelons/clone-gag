package com.strautins.CloneGag.controllers;

import com.strautins.CloneGag.definitions.FeedType;
import com.strautins.CloneGag.exceptions.ExceptionManager;
import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.pojo.PostPage;
import com.strautins.CloneGag.pojo.PostResponse;
import com.strautins.CloneGag.pojo.VoteResponse;
import com.strautins.CloneGag.service.PostService;
import com.strautins.CloneGag.service.UserService;
import com.strautins.CloneGag.service.VoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/post")
public class PostController {

    private static final Logger LOG = LogManager.getLogger(PostController.class.getName());

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    /**
     * Returns the post feed jsp.
     * User does not need to be logged in.
     *
     * @param type
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"", "/", "/feed/{type}"}, method = RequestMethod.GET)
    public String getPostFeed(@PathVariable(value = "type", required = false) Optional<FeedType> type, ModelMap modelMap) {
        FeedType feedType = type.isPresent() ? type.get() : FeedType.FRESH;

        modelMap.addAttribute("pageTitle", "See what the cat dragged in");
        modelMap.addAttribute("isLoggedIn", userService.isLoggedIn());
        modelMap.addAttribute("feedType", feedType.getFeedType());

        return "feed";
    }

    /**
     * Returns the new post jsp.
     * User must be logged in.
     */
    /**
     * Returns the new post creation menu.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newPost(ModelMap modelMap) {
        Post post = new Post();
        modelMap.addAttribute("post", post);
        modelMap.addAttribute("pageTitle", "New Post");
        modelMap.addAttribute("isLoggedIn", userService.isLoggedIn());
        return "newPost";
    }

    /**
     * Handles new post upload.
     */
    /**
     * Saves a post in the database.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String newPost(@ModelAttribute("post") @Valid Post post, BindingResult result, ModelMap modelMap) {

        if (result.hasErrors()) {
            return "newPost";
        }

        post.setCreateDate(new Date());
        post.setOwner(userService.getCurrentUserId());

        postService.savePost(post);

        modelMap.addAttribute("title", post.getTitle());
        modelMap.addAttribute("tags", post.getTags());
        modelMap.addAttribute("image", post.getBase64EncodedImage());

        modelMap.addAttribute("pageTitle", "Your New Post");
        modelMap.addAttribute("isLoggedIn", userService.isLoggedIn());
        return "viewPost";
    }

    /**
     * Returns the view post jsp.
     */
    /**
     * View a single post by id.
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/view/{id}"}, method = RequestMethod.GET)
    public String viewPost(@PathVariable(value = "id") BigInteger id, ModelMap modelMap) {
        LOG.debug("PostController: Loading post with id: " + id);

        Post post = postService.loadPost(id);

        modelMap.addAttribute("title", post.getTitle());
        modelMap.addAttribute("tags", post.getTags());
        modelMap.addAttribute("image", post.getBase64EncodedImage());

        modelMap.addAttribute("pageTitle", "A Fancy Post");
        modelMap.addAttribute("isLoggedIn", userService.isLoggedIn());
        return "viewPost";
    }

    /**
     * Returns a list of user posts jsp.
     * User must be logged in.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPosts(ModelMap modelMap) {
        modelMap.addAttribute("pageTitle", "Your fancy posts");
        modelMap.addAttribute("isLoggedIn", userService.isLoggedIn());
        modelMap.addAttribute("userId", userService.getCurrentUserId());

        return "listPosts";
    }

    /**
     * Returns the up vote stream jsp.
     * User must be logged in.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/upvotes", method = RequestMethod.GET)
    public String myUpvotes(ModelMap modelMap) {
        modelMap.addAttribute("userId", userService.getCurrentUserId());
        return "upvotedStream";
    }

    /**
     * Returns the up vote stream jsp.
     * User must be logged in.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/upvotes/{userId}", method = RequestMethod.GET)
    public String userUpvotes(@PathVariable("userId") BigInteger userId, ModelMap modelMap) {
        modelMap.addAttribute("userId", userId);
        return "upvotedStream";
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

    /**
     * Returns user up voted posts for a user, select pages.
     *
     * @param userId
     * @param page
     * @return
     * @throws RestException
     */
    @RequestMapping(value = "/votes/{userId}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<PostResponse> getUpVotedPostStream(
            @PathVariable(value = "userId", required = false) BigInteger userId,
            @PathVariable(value = "page", required = false) BigInteger page
    ) throws RestException {
        try {
            return handleUpVoteStream(userId, page);
        } catch (Exception e) {
            throw ExceptionManager.InternalError(e);
        }
    }

    private List<PostResponse> handleUpVoteStream(BigInteger userId, BigInteger page) throws RestException {
        if (userId == null) {
            userId = userService.getCurrentUserId();
        }
        return voteService.getUpVotedPosts(userId, page);
    }

    /**
     * Returns a selected page of posts depending on the feed type.
     *
     * @param type
     * @return
     * @throws RestException
     */
    @RequestMapping(value = "/feedStream/{type}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<PostResponse> getPostFeedStream(
            @PathVariable("type") FeedType type,
            @PathVariable("page") BigInteger page
    ) throws RestException {
        try {
            return handleFeedStream(type, page);
        } catch (Exception e) {
            throw ExceptionManager.InternalError(e);
        }
    }

    public List<PostResponse> handleFeedStream(FeedType type, BigInteger page) throws RestException {
        return postService.getFeed(type, page);
    }

    /**
     * Returns paginated user posts.
     *
     * @return
     * @throws RestException
     */
    @RequestMapping(value = "/{userId}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public PostPage getUserPostPages(
            @PathVariable(value = "userId", required = false) BigInteger userId,
            @PathVariable("page") BigInteger page
    ) throws RestException {
        try {
            return postService.getUserPosts(userId, page);
        } catch (Exception e) {
            throw ExceptionManager.InternalError(e);
        }
    }


}
