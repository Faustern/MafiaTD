package com.tyhyidon.faust.game.manager;

import com.tyhyidon.faust.game.entity.User;
import com.tyhyidon.faust.game.entity.mapping.PlayerEntityConverter;
import com.tyhyidon.faust.game.entity.mapping.UserEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by vasylsavytskyi on 29.12.14.
 */
@Repository
public class UserManagerImpl implements UserManager {

    private static final Logger LOG = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    private JdbcTemplate template;

    @Override
    public User getUserByUsername(String username){
        return template.queryForObject("SELECT * FROM User WHERE username = ?", new UserEntityConverter(), username);
    }

}
