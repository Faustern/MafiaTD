package com.tyhyidon.faust.game.managers;

import com.tyhyidon.faust.game.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class UserManagerImpl {

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
