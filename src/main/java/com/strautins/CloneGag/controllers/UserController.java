package com.strautins.CloneGag.controllers;

import com.strautins.CloneGag.model.CloneGagUser;
import com.strautins.CloneGag.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap modelMap) {
        CloneGagUser user = new CloneGagUser();

        modelMap.addAttribute("user", user);
        modelMap.addAttribute("pageTitle", "Register");

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Valid CloneGagUser user, BindingResult result, ModelMap modelMap) {
        if (userService.isUsernameTaken(user.getUsername())) {
            result.rejectValue("username", "com.strautins.CloneGag.model.register.username", "So fancy, It's already taken.");
        }

        if (result.hasErrors()) {
            LOG.debug(result.getAllErrors());
            return "register";
        }

        userService.save(user);

        return "redirect:/login";
    }
}
