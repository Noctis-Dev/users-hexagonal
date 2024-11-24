package com.qride.users.application.dto.response;


import com.qride.users.domain.models.enums.UserStatus;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponse(
    UUID userUuid,
    LocalDate createdAt,
    LocalDate activatedAt,
    LocalDate deletedAt,
    UserStatus status
) { }
