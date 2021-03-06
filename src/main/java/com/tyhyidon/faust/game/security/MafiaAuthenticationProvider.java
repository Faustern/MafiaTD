package com.tyhyidon.faust.game.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by vasylsavytskyi on 29.12.14.
 */
@Component
public class MafiaAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
        if (loadedUser == null){
            throw new BadCredentialsException("User does not exist");
        }
        if (isPasswordValid(loadedUser.getUsername(), authentication.getCredentials().toString())){
            return loadedUser;
        }
        throw new BadCredentialsException("Invalid Password");
    }

    private boolean isPasswordValid(String username, String password) {
        try {
            return password.equals("komdon");
        } catch (Exception e) {
           LOG.debug(String.format("Unable to encode %s password ['%s']", username, password));
        }
        return false;
        //TODO
    }


}
