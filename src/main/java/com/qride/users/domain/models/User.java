package com.qride.users.domain.models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;
import com.qride.users.domain.models.enums.UserStatus;

import java.time.LocalDate;


@Getter @Setter
public class User {
    private Long id;

    @UUID
    @NotNull
    private java.util.UUID userUuid;

    @NotNull
    private LocalDate createdAt;

    private LocalDate activatedAt;

    private LocalDate deletedAt;

    @NotNull
    private UserStatus status;

    @NotNull
    private Contact contact;
}