package com.tyhyidon.faust.game.manager;

import com.tyhyidon.faust.game.entity.User;

/**
 * Created by vasylsavytskyi on 22.01.15.
 */
public interface UserManager {

    public User getUserByUsername(String username);
}
