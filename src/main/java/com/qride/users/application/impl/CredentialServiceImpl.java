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

    @Override
    public Credential findByEmail(String email) {
        return repository.findByEmail(email);
    }

    private Credential toCredential(CredentialRequest request) {
        Credential credential = new Credential();

        credential.setCredentialUuid(UUID.randomUUID());
        credential.setPassword(request.password());
        credential.setCreatedAt(LocalDate.now());

        return credential;
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter");
        }
        if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Password must contain at least one lowercase letter");
        }
        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must contain at least one number");

        }
}
}