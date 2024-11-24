package com.qride.users.application.impl;

import com.qride.users.application.ICredentialService;
import com.qride.users.domain.models.Contact;
import com.qride.users.domain.models.Credential;
import com.qride.users.domain.models.Token;
import com.qride.users.domain.repository.ICredentialRepository;
import com.qride.users.application.dto.request.CredentialRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CredentialServiceImpl implements ICredentialService {

    @Autowired
    private ICredentialRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Credential create(CredentialRequest request, Token token, Contact contact) {
        Credential credential = toCredential(request);
        credential.setContact(contact);
        credential.setToken(token);
        credential.setPassword(encoder.encode(credential.getPassword()));
        return repository.create(credential);
    }

    @Override
    public Credential find(UUID credentialUuid) {
        return repository.find(credentialUuid);
    }

    @Override
    public Credential find(String phoneNumber) {
        return repository.find(phoneNumber);
    }

    private Credential toCredential(CredentialRequest request) {
        Credential credential = new Credential();

        credential.setCredentialUuid(UUID.randomUUID());
        credential.setPassword(request.password());
        credential.setCreatedAt(LocalDate.now());

        return credential;
    }
}