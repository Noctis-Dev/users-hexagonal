package com.qride.users.application;


import com.qride.users.domain.models.TokenSetting;
import com.qride.users.domain.models.enums.TokenType;

public interface ITokenSettingsService {
    TokenSetting find(TokenType type);
}
