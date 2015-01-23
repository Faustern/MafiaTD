package com.tyhyidon.faust.game.entity.mapping;

import com.tyhyidon.faust.game.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vasylsavytskyi on 23.01.15.
 */
public class UserEntityConverter implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rowSet, int i) throws SQLException {
        User user = new User();
        user.setUsername(rowSet.getString("username"));
        user.setPassword(rowSet.getString("password"));
        return user;
    }
}
