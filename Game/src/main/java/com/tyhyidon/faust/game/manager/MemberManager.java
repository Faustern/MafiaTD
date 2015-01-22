package com.tyhyidon.faust.game.manager;

import com.tyhyidon.faust.game.entity.Member;

import java.util.List;

/**
 * Created by vasylsavytskyi on 22.01.15.
 */
public interface MemberManager {

    public List<Member> getMembers();

    public void addMember(String nickname);
}
