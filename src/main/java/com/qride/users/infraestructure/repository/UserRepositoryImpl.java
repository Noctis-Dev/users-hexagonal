package com.qride.users.infraestructure.repository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import com.qride.users.domain.models.User;
import com.qride.users.domain.repository.IUserRepository;
import com.qride.users.infraestructure.mapper.IUserMapper;
import com.qride.users.infraestructure.repository.jpa.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    @Qualifier("userMapperImpl")
    private IUserMapper mapper;

    @Autowired
    private UserEntityRepository jpaRepository;

    @Override
    public User create(@Valid User userEntity) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(userEntity)));
    }

    @Override
    public User find(UUID userUuid) {
        return jpaRepository.findByUserUuid(userUuid)
                .map(mapper::toDomain).orElseThrow(EntityNotFoundException::new);
    }
}
