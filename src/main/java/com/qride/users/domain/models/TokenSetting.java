package com.qride.users.domain.models;

import lombok.Getter;
import lombok.Setter;
import com.qride.users.domain.models.enums.TokenType;

import java.io.Serializable;

@Getter @Setter
public class TokenSetting implements Serializable {
    private Long id;
    private Boolean isActive;
    private Integer tokenExpiration;
    private Integer refreshTokenExpiration;
    private TokenType tokenType;
}