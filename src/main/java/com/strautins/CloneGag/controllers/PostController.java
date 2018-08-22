package com.strautins.CloneGag.controllers;

import com.strautins.CloneGag.model.Post;
import com.strautins.CloneGag.security.CloneGagUserDetails;
import com.strautins.CloneGag.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;
import java.util.Date;

@Controller
@RequestMapping("/post")
public class PostController {

    private static final Logger LOG = LogManager.getLogger(PostController.class.getName());

    @Autowired
    private PostService postService;

    /**
     * Returns the new post creation menu.
     *
     * @param modelMap ModelMap
     * @return
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newPost(ModelMap modelMap) {
        LOG.debug("PostController: /new");
        Post post = new Post();
        modelMap.addAttribute("post", post);
        modelMap.addAttribute("pageTitle", "New Post");
        return "newPost";
    }

    /**
     * Saves a post in the database.
     *
     * @param modelMap ModelMap
     * @return
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String newPost(@ModelAttribute("post") @Validated Post post, BindingResult result, ModelMap modelMap) {
        post.setCreateDate(new Date());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof CloneGagUserDetails) {
            post.setOwner(((CloneGagUserDetails) principal).getId());
        }

        postService.savePost(post);

        modelMap.addAttribute("title", post.getTitle());
        modelMap.addAttribute("tags", post.getTags());
        modelMap.addAttribute("image", post.getBase64EncodedImage());

        modelMap.addAttribute("pageTitle", "Your New Post");
        return "viewPost";
    }

    @RequestMapping(value = {"/view/{id}"}, method = RequestMethod.GET)
    public String viewPost(@PathVariable(value = "id") BigInteger id, ModelMap modelMap) {
        LOG.debug("PostController: Loading post with id: " + id);

        Post post = postService.loadPost(id);

        modelMap.addAttribute("title", post.getTitle());
        modelMap.addAttribute("tags", post.getTags());
        modelMap.addAttribute("image", post.getBase64EncodedImage());

        modelMap.addAttribute("pageTitle", "A Fancy Post");
        return "viewPost";
    }
}
