package com.qride.users.application.impl;

import com.qride.users.application.ITokenService;
import com.qride.users.application.ITokenSettingsService;
import com.qride.users.domain.models.Token;
import com.qride.users.domain.models.TokenSetting;
import com.qride.users.domain.models.enums.TokenType;
import com.qride.users.domain.repository.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;



@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private ITokenRepository repository;

    @Autowired
    private ITokenSettingsService tokenSettingsService;

    @Override
    public Token createToken() {
        TokenSetting tokenSetting = tokenSettingsService.find(TokenType.VERIFICATION);

        Token token = new Token();
        token.setToken(generateSixDigitCode());
        token.setTokenType(TokenType.VERIFICATION);
        token.setExpirationDate(LocalDate.now().plusDays(tokenSetting.getTokenExpiration()));
        token.setTokenSetting(tokenSetting);

        return repository.save(token);
    }

    @Override
    public Token find(String token) {
        return repository.find(token);
    }

    private String generateSixDigitCode() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }
}
