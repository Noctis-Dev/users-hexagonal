package com.qride.users.domain.repository;

import jakarta.validation.Valid;
import com.qride.users.domain.models.User;

import java.util.UUID;

public interface IUserRepository {

    User create(@Valid User userEntity);
    User find(UUID userUuid);

}
