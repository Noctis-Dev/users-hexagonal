package com.qride.users.application.impl;

import com.qride.users.application.ITokenSettingsService;
import com.qride.users.domain.models.TokenSetting;
import com.qride.users.domain.models.enums.TokenType;
import com.qride.users.domain.repository.ITokenSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenSettingsServiceImpl implements ITokenSettingsService {

    @Autowired
    private ITokenSettingsRepository repository;

    @Override
    public TokenSetting find(TokenType type) {
        return repository.findActive(type);
    }
}
