package com.strautins.CloneGag.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class GagController {
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(ModelMap modelMap) {
        return "login";
    }


    @RequestMapping(value = {"/denied"}, method = RequestMethod.GET)
    public String denied(ModelMap modelMap) {
        return "accessDenied";
    }
}
