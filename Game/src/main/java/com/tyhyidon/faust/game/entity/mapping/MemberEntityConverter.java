package com.tyhyidon.faust.game.entity.mapping;

import com.tyhyidon.faust.game.entity.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 */
public class MemberEntityConverter implements RowMapper<Member> {

    public Member mapRow(ResultSet rowSet, int rowNum) throws SQLException {
        Member member = new Member();
        member.setNickname(rowSet.getString("nickname"));
        return member;
    }
}
