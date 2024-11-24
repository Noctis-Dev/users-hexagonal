package com.qride.users.infraestructure.repository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import com.qride.users.domain.models.Credential;
import com.qride.users.domain.repository.ICredentialRepository;
import com.qride.users.infraestructure.mapper.ICredentialMapper;
import com.qride.users.infraestructure.repository.jpa.CredentialEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CredentialRepositoryImpl implements ICredentialRepository {

    @Autowired
    private ICredentialMapper mapper;

    @Autowired
    private CredentialEntityRepository jpaRepository;

    @Override
    public Credential create(@Valid Credential credentialEntity) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(credentialEntity)));
    }

    @Override
    public Credential find(UUID credentialUuid) {
        return jpaRepository.findByCredentialUuid(credentialUuid)
                .map(mapper::toDomain).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Credential find(String phoneNumber) {
        return jpaRepository.find(phoneNumber)
                .map(mapper::toDomain).orElseThrow(EntityNotFoundException::new);
    }
}
