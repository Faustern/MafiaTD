package com.tyhyidon.faust.game.managers;

import com.tyhyidon.faust.game.entity.Member;
import com.tyhyidon.faust.game.entity.mapping.MemberEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class GameManagerImpl {

    private static final Logger LOG = LoggerFactory.getLogger(GameManagerImpl.class);

    @Autowired
    private JdbcTemplate template;

    public List<Member> getAllMembers() {

        return template.query(" SELECT CovDateSpecified, CovNoDays, CovNoMonths FROM IRISCDU icdu " +
                        " inner join IRIS i " +
                        " on icdu.IrisId = i.IrisID " +
                        " inner join IRISObligor iob " +
                        " on iob.IrisId = i.IrisID " +
                        " WHERE ObligorId = ? ",
                new MemberEntityConverter(),
                1);
    }
}
