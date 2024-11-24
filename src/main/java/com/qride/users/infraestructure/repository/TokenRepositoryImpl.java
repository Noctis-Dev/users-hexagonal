package com.qride.users.infraestructure.repository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import com.qride.users.domain.models.Token;
import com.qride.users.domain.repository.ITokenRepository;
import com.qride.users.infraestructure.mapper.ITokenMapper;
import com.qride.users.infraestructure.repository.jpa.TokenEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenRepositoryImpl implements ITokenRepository {

    @Autowired
    private ITokenMapper mapper;

    @Autowired
    private TokenEntityRepository jpaRepository;

    @Override
    public Token save(@Valid Token token) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(token)));
    }

    @Override
    public Token find(String token) {
        return jpaRepository.findByToken(token).map(mapper::toDomain).orElseThrow(EntityNotFoundException::new);
    }
}
