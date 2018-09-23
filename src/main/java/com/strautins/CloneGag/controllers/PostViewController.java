package com.strautins.CloneGag.controllers;

import com.strautins.CloneGag.definitions.FeedType;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.service.PostService;
import com.strautins.CloneGag.service.UserService;
import com.strautins.CloneGag.service.VoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

/**
 * View endpoint for returning pages regarding posts.
 */
@Controller
@RequestMapping("/post")
public class PostViewController {

    private static final Logger LOG = LogManager.getLogger(PostViewController.class.getName());

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
    public String postFeed(@PathVariable(value = "type", required = false) Optional<FeedType> type, ModelMap modelMap) {
        FeedType feedType = type.isPresent() ? type.get() : FeedType.FRESH;

        modelMap.addAttribute("pageTitle", "See what the cat dragged in");
        modelMap.addAttribute("isLoggedIn", userService.isLoggedIn());
        modelMap.addAttribute("feedType", feedType.getFeedType());

        return "feed";
    }

    /**
     * Returns the new post creation menu.
     * User must be logged in.
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
     * User does not have to be logged in.
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/view/{id}"}, method = RequestMethod.GET)
    public String viewPost(@PathVariable(value = "id") BigInteger id, ModelMap modelMap) {
        LOG.debug("PostController: Loading post with id: " + id);

        Post post = postService.loadPost(id);

        modelMap.addAttribute("postId", post.getId());
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
     * User must be logged in, as its for the current user.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/upvotes", method = RequestMethod.GET)
    public String myUpvotes(ModelMap modelMap) {
        modelMap.addAttribute("userId", userService.getCurrentUserId());
        modelMap.addAttribute("isLoggedIn", userService.isLoggedIn());
        return "upvotedStream";
    }

    /**
     * Returns the up vote stream jsp.
     * User does not have to be logged in, as its for a specific user.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/upvotes/{userId}", method = RequestMethod.GET)
    public String userUpvotes(@PathVariable("userId") BigInteger userId, ModelMap modelMap) {
        modelMap.addAttribute("userId", userId);
        modelMap.addAttribute("isLoggedIn", userService.isLoggedIn());
        return "upvotedStream";
    }
}
