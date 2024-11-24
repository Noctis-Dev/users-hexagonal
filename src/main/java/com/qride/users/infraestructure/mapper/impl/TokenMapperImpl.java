package com.qride.users.infraestructure.mapper.impl;

import com.qride.users.domain.models.Token;
import com.qride.users.domain.models.enums.TokenType;
import com.qride.users.infraestructure.entities.TokenEntity;
import com.qride.users.infraestructure.entities.enums.TokenTypeEntity;
import com.qride.users.infraestructure.mapper.ITokenMapper;
import com.qride.users.infraestructure.mapper.ITokenSettingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenMapperImpl implements ITokenMapper {

    @Autowired
    private ITokenSettingsMapper tokenSettingsMapper;

    @Override
    public Token toDomain(TokenEntity entity) {
        Token token = new Token();

        token.setId(entity.getId());
        token.setToken(entity.getToken());
        token.setExpirationDate(entity.getExpirationDate());
        token.setTokenType(TokenType.valueOf(entity.getTokenTypeEntity().name()));
        token.setTokenSetting(tokenSettingsMapper.toDomain(entity.getTokenSettingEntity()));

        return token;
    }

    @Override
    public TokenEntity toEntity(Token model) {
        TokenEntity tokenEntity = new TokenEntity();

        tokenEntity.setId(model.getId());
        tokenEntity.setToken(model.getToken());
        tokenEntity.setExpirationDate(model.getExpirationDate());
        tokenEntity.setTokenTypeEntity(TokenTypeEntity.valueOf(model.getTokenType().name()));
        tokenEntity.setTokenSettingEntity(tokenSettingsMapper.toEntity(model.getTokenSetting()));

        return tokenEntity;
    }
}
