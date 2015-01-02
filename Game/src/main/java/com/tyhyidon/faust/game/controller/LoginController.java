package com.tyhyidon.faust.game.controller;

import com.tyhyidon.faust.game.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by vasylsavytskyi on 29.12.14.
 */
@Controller
public class LoginController {

    private Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (SecurityUtils.getCurrentUser() != null) {
            return "redirect:/index";
        }
        return "login";
    }

    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String home() {
        return "/index";
    }
}
