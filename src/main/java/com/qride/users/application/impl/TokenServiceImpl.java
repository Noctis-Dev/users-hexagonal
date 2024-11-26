package com.qride.users.application.impl;

import com.qride.users.application.ITokenService;
import com.qride.users.domain.models.Token;
import com.qride.users.domain.models.enums.TokenType;
import com.qride.users.domain.repository.ITokenRepository;
import com.qride.users.infraestructure.configuration.security.jwt.TokenSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;



@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private ITokenRepository repository;

    @Autowired
    private TokenSettings tokenSettings;

    @Override
    public Token createToken() {

        Token token = new Token();
        token.setToken(generateFourDigitCode());
        token.setTokenType(TokenType.VERIFICATION);
        token.setExpirationDate(LocalDate.now().plusDays(tokenSettings.getJwtTokenExpirationDays()));

        return repository.save(token);
    }

    @Override
    public Token find(String token) {
        return repository.find(token);
    }

    private String generateFourDigitCode() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }}
