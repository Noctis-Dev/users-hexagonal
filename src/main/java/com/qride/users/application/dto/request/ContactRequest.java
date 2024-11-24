package com.qride.users.application.dto.request;

import com.qride.users.domain.events.EventType;

public record ContactRequest(
    EventType type,
    String phoneNumber,
    String email
) { }