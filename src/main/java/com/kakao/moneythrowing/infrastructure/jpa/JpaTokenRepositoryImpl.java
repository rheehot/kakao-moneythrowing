package com.kakao.moneythrowing.infrastructure.jpa;

import com.kakao.moneythrowing.domain.model.common.Token;
import com.kakao.moneythrowing.domain.model.common.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@RequiredArgsConstructor
@Repository
public class JpaTokenRepositoryImpl implements TokenRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public boolean checkTokenUsed(Class<?> entityClass, Token token) {
        Query query = entityManager.createQuery(
                "SELECT 1 FROM " + entityClass.getName() + " c WHERE c.token = :token");
        query.setParameter("token", token);

        return query.getResultList().size() == 1;
    }
}
