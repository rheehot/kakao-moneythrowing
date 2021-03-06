package com.kakao.moneythrowing.domain.model.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenGenerator {
    private final TokenRepository tokenRepository;

    // TODO 동시성 이슈 발생 가능
    public Token generateUnusedToken(Class<?> entityClass) {
        Token token;
        do {
            token = Token.generate();
        } while (tokenRepository.checkTokenUsed(entityClass, token));

        return token;
    }
}
