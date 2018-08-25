package com.strautins.CloneGag.controllers;

import com.strautins.CloneGag.definitions.FeedType;
import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.service.PostService;
import com.strautins.CloneGag.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController {

    private static final Logger LOG = LogManager.getLogger(PostController.class.getName());

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/", "/{type}"}, method = RequestMethod.GET)
    public String home(@PathVariable(value = "id", required = false) Optional<FeedType> type, ModelMap modelMap) {
        // TODO use this later to decide
        FeedType feedType = type.isPresent() ? type.get() : FeedType.FRESH;

        List<Post> posts = postService.getFresh();

        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("pageTitle", "See what the cat dragged in");
        modelMap.addAttribute("isLoggedIn", userService.isLoggedIn());

        return "listPosts";
    }

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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPosts(ModelMap modelMap) {

        List<Post> posts = postService.getUserPosts(userService.getCurrentUserId());

        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("pageTitle", "Your fancy posts");
        modelMap.addAttribute("isLoggedIn", userService.isLoggedIn());

        return "listPosts";
    }
}
