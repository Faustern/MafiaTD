package com.tyhyidon.faust.game.manager;

import com.tyhyidon.faust.game.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by vasylsavytskyi on 29.12.14.
 */
@Repository
public class UserManagerImpl implements UserManager {

    private static final Logger LOG = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    private JdbcTemplate template;

    @PersistenceContext
    private EntityManager entityManager;

    public User getUserByUsername(String username){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> user = criteriaQuery.from(User.class);
        criteriaQuery.select(user).where(criteriaBuilder.equal(user.get("username"), username));
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

}
