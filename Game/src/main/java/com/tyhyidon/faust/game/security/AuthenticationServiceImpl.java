package com.tyhyidon.faust.game.security;

import com.tyhyidon.faust.game.managers.UserManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by vasylsavytskyi on 29.12.14.
 */
@Service
public class AuthenticationServiceImpl implements UserDetailsService{

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private UserManagerImpl userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userManager.getUserByUsername(username);
        } catch (Exception e) {
            LOG.debug(String.format("Query returned no results for username ['%s']", username), e);
            return null;
        }
    }
}