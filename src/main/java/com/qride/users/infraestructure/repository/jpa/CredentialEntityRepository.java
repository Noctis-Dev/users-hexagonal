package com.qride.users.infraestructure.repository.jpa;

import com.qride.users.infraestructure.entities.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CredentialEntityRepository extends JpaRepository<CredentialEntity, Long> {

    Optional<CredentialEntity> findByCredentialUuid(UUID credentialUuid);

    @Query(value = """
        SELECT c.* FROM credentials c INNER JOIN contacts co WHERE co.phone_number = :phoneNumber
    """, nativeQuery = true)
    Optional<CredentialEntity> find(String phoneNumber);

}