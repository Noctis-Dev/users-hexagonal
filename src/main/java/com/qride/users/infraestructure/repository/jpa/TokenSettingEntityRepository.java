package com.qride.users.infraestructure.repository.jpa;

import com.qride.users.infraestructure.entities.TokenSettingEntity;
import com.qride.users.infraestructure.entities.enums.TokenTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenSettingEntityRepository extends JpaRepository<TokenSettingEntity, Long> {

    Optional<TokenSettingEntity> findByTokenTypeEntityAndIsActive(TokenTypeEntity type, boolean isActive);

}