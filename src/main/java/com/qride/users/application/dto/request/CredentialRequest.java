package com.qride.users.application.dto.request;

import com.qride.users.domain.events.EventType;

import java.util.UUID;

public record CredentialRequest(
    EventType type,
    UUID contactUuid,
    String password
) {}
