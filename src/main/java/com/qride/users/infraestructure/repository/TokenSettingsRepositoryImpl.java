package com.qride.users.infraestructure.repository;

import jakarta.persistence.EntityNotFoundException;
import com.qride.users.domain.models.TokenSetting;
import com.qride.users.domain.models.enums.TokenType;
import com.qride.users.domain.repository.ITokenSettingsRepository;
import com.qride.users.infraestructure.entities.enums.TokenTypeEntity;
import com.qride.users.infraestructure.mapper.ITokenSettingsMapper;
import com.qride.users.infraestructure.repository.jpa.TokenSettingEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenSettingsRepositoryImpl implements ITokenSettingsRepository {

    @Autowired
    private ITokenSettingsMapper mapper;

    @Autowired
    private TokenSettingEntityRepository jpaRepository;

    @Override
    public TokenSetting findActive(TokenType type) {
        return jpaRepository.findByTokenTypeEntityAndIsActive(TokenTypeEntity.valueOf(type.name()), true)
                .map(mapper::toDomain).orElseThrow(EntityNotFoundException::new);
    }
}
