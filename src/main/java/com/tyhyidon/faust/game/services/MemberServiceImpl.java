package com.tyhyidon.faust.game.services;

import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * Created by vasylsavytskyi on 05.05.15.
 */
@Service
public class MemberServiceImpl {

    @Autowired
    private MemberRepository memberRepository;

    public Member addUpdate(Member member) {
        return memberRepository.save(member);
    }

    public Member find(String nickname) {
        return memberRepository.findOne(nickname);
    }

    public void remove(Member member) {
        memberRepository.delete(member);
    }

    public long amount() {
        return memberRepository.count();
    }

    public List<String> allNicknames() {
        return StreamSupport.stream(memberRepository.findAll().spliterator(), true).
                map(Member::getNickname).collect(toList());
    }

    public List<Member> all() {
        return StreamSupport.stream(memberRepository.findAll().spliterator(), true).collect(toList());
    }
}
