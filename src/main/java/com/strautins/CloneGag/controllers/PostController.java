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
        return "newPost";
    }

    /**
     * Saves a post in the database.
     *
     * @param modelMap ModelMap
     * @return
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String newPost(Post post, BindingResult result, ModelMap modelMap) {
        post.setCreateDate(new Date());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof CloneGagUserDetails) {
            post.setOwner(((CloneGagUserDetails) principal).getId());
        }

        postService.savePost(post);

        modelMap.addAttribute("post", post);

        return "viewPost";
    }

    @RequestMapping(value = {"/view/{id}"}, method = RequestMethod.GET)
    public String viewPost(@PathVariable(value = "id", required = false) BigInteger id, ModelMap modelMap) {
        // TODO load in post from db
        return "viewPost";
    }
}
