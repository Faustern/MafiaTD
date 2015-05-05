package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by vasylsavytskyi on 05.05.15.
 */
public class MemberServiceImpl {

    @Autowired
    private MemberRepository memberRepository;
}
