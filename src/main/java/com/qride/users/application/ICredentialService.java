package com.qride.users.application;

import com.qride.users.domain.models.Contact;
import com.qride.users.domain.models.Credential;
import com.qride.users.domain.models.Token;
import com.qride.users.application.dto.request.CredentialRequest;

import java.util.UUID;

public interface ICredentialService {

    Credential create(CredentialRequest request, Token token, Contact contact);
    Credential find(UUID credentialUuid);
    Credential find(String phoneNumber);
    Credential findByEmail(String email);
}
