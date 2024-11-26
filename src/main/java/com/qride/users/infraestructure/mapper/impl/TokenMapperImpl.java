package com.qride.users.infraestructure.mapper.impl;

import com.qride.users.domain.models.Token;
import com.qride.users.domain.models.enums.TokenType;
import com.qride.users.infraestructure.entities.TokenEntity;
import com.qride.users.infraestructure.entities.enums.TokenTypeEntity;
import com.qride.users.infraestructure.mapper.ITokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenMapperImpl implements ITokenMapper {

    @Override
    public Token toDomain(TokenEntity entity) {
        Token token = new Token();

        token.setId(entity.getId());
        token.setToken(entity.getToken());
        token.setExpirationDate(entity.getExpirationDate());
        token.setTokenType(TokenType.valueOf(entity.getTokenTypeEntity().name()));

        return token;
    }

    @Override
    public TokenEntity toEntity(Token model) {
        TokenEntity tokenEntity = new TokenEntity();

        tokenEntity.setId(model.getId());
        tokenEntity.setToken(model.getToken());
        tokenEntity.setExpirationDate(model.getExpirationDate());
        tokenEntity.setTokenTypeEntity(TokenTypeEntity.valueOf(model.getTokenType().name()));

        return tokenEntity;
    }
}
