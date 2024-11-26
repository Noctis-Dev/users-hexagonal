package com.qride.users.domain.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.qride.users.domain.models.enums.TokenType;

import java.time.LocalDate;

@Getter @Setter
public class Token {
    private Long id;

    @NotBlank
    private String token;

    @NotNull
    private TokenType tokenType;

    @NotNull
    private LocalDate expirationDate;
}