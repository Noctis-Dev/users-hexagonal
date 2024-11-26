package com.qride.users.application.dto.request;

public record LogInRequest(
    String email,
    String password,
    String phoneNumber
) {}
