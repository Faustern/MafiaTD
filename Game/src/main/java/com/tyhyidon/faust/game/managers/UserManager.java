package com.tyhyidon.faust.game.managers;

import com.tyhyidon.faust.game.model.User;

/**
 * Created by vasylsavytskyi on 29.12.14.
 */
public interface UserManager {

    User getUserByUsername(String username);
}
