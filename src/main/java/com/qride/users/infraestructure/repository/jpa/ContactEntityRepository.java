package com.qride.users.infraestructure.repository.jpa;

import com.qride.users.infraestructure.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContactEntityRepository extends JpaRepository<ContactEntity, Long> {

    Optional<ContactEntity> findByContactUuid(UUID uuid);

}