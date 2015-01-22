package com.tyhyidon.faust.game.manager;

import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.mapping.MemberEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vasylsavytskyi on 22.01.15.
 */
@Repository
public class MemberManagerImpl implements MemberManager{

    private static final Logger LOG = LoggerFactory.getLogger(MemberManagerImpl.class);

    @Autowired
    private JdbcTemplate template;

    public List<Member> getMembers() {
        return template.query("SELECT * FROM Member", new MemberEntityConverter());
    }

    public void addMember(String nickname) {
        template.update("INSERT INTO Member (nickname) VALUES ('" + nickname + "')");
    }

}
