package com.tyhyidon.faust.game.utils;

import com.tyhyidon.faust.game.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by vasylsavytskyi on 01.01.15.
 */
public class SecurityUtils {

    private  static final Logger LOG = LoggerFactory.getLogger(SecurityUtils.class);

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}
