package com.qride.users.domain.repository;


import com.qride.users.domain.models.TokenSetting;
import com.qride.users.domain.models.enums.TokenType;

public interface ITokenSettingsRepository {
    TokenSetting findActive(TokenType type);
}
