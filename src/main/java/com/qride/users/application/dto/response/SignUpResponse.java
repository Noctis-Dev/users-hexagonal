package com.qride.users.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class SignUpResponse {
    private UUID userUuid;
    private UUID credentialUuid;
    private UUID contactUuid;
}
