package com.strautins.CloneGag.controllers;

import com.strautins.CloneGag.model.CloneGagUser;
import com.strautins.CloneGag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap modelMap) {
        CloneGagUser user = new CloneGagUser();

        modelMap.addAttribute("user", user);
        modelMap.addAttribute("pageTitle", "Register");

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") CloneGagUser user, BindingResult result, ModelMap modelMap) {

        return "register";
    }
}
