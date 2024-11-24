package com.qride.users.infraestructure.repository.jpa;

import com.qride.users.infraestructure.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenEntityRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByToken(String token);

}