package com.qride.users.domain.repository;

import jakarta.validation.Valid;
import com.qride.users.domain.models.Credential;

import java.util.UUID;

public interface ICredentialRepository {

    Credential create(@Valid Credential credentialEntity);
    Credential find(UUID credentialUuid);
    Credential find(String phoneNumber);

}
