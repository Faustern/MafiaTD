package com.tyhyidon.faust.game.repositories;

import com.tyhyidon.faust.game.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by vasylsavytskyi on 25.04.15.
 */
public interface MemberRepository extends CrudRepository<Member, String>{

    @Query("SELECT m FROM #{#entityName} m LEFT JOIN FETCH m.players WHERE m.nickname = (:nickname)")
    Member findByNicknameAndFetchPlayersEagerly(@Param("nickname") String nickname);
}
