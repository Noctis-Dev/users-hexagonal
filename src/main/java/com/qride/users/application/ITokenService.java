package com.qride.users.application;

import com.qride.users.domain.models.Token;

public interface ITokenService {
    Token createToken();
    Token find(String token);
}
