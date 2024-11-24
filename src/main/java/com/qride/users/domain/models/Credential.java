package com.qride.users.domain.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
public class Credential {
    private Long id;

    @NotNull
    @org.hibernate.validator.constraints.UUID
    private UUID credentialUuid;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    private LocalDate verifiedAt;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private Token token;

    private Contact contact;
}