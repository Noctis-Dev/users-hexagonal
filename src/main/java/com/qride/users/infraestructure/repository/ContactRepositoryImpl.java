package com.qride.users.infraestructure.repository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import com.qride.users.domain.models.Contact;
import com.qride.users.domain.repository.IContactRepository;
import com.qride.users.infraestructure.mapper.IContactMapper;
import com.qride.users.infraestructure.repository.jpa.ContactEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContactRepositoryImpl implements IContactRepository {

    @Autowired
    private IContactMapper mapper;

    @Autowired
    private ContactEntityRepository jpaRepository;

    @Override
    public Contact create(@Valid Contact contact) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(contact)));
    }

    @Override
    public Contact find(UUID uuid) {
        return jpaRepository.findByContactUuid(uuid).map(mapper::toDomain).orElseThrow(EntityNotFoundException::new);
    }
}
