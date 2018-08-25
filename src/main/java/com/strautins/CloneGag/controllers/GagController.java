package com.strautins.CloneGag.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class GagController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/post";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(ModelMap modelMap) {
        return "login";
    }


    @RequestMapping(value = {"/denied"}, method = RequestMethod.GET)
    public String denied(ModelMap modelMap) {
        return "accessDenied";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFound(ModelMap modelMap) {
        modelMap.addAttribute("error", "Oh, hello there, 404.");
        modelMap.addAttribute("pageTitle", "404");
        return "error";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String internalError(ModelMap modelMap) {
        modelMap.addAttribute("error", "Very sorry, something went wrong.");
        modelMap.addAttribute("pageTitle", "500");
        return "error";
    }
}
