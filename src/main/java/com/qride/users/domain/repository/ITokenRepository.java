package com.qride.users.domain.repository;

import jakarta.validation.Valid;
import com.qride.users.domain.models.Token;

public interface ITokenRepository {
    Token save(@Valid Token token);
    Token find(String token);
}
