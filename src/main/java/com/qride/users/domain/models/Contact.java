package com.qride.users.domain.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
public class Contact {
    private Long id;

    @NotNull
    @org.hibernate.validator.constraints.UUID
    private UUID contactUuid;

    @Size(max = 15)
    private String phoneNumber;

    @Size(max = 70)
    @Email
    private String email;

    private LocalDate createdAt;

    private User user;
}