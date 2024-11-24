package com.qride.users.infraestructure.mapper.impl;

import com.qride.users.domain.models.User;
import com.qride.users.domain.models.enums.UserStatus;
import com.qride.users.infraestructure.entities.UserEntity;
import com.qride.users.infraestructure.entities.enums.UserStatusEntity;
import com.qride.users.infraestructure.mapper.IUserMapper;
import org.springframework.stereotype.Component;

@Component
public class ContactUserMapperImpl implements IUserMapper {
    @Override
    public User toDomain(UserEntity entity) {
        User user = new User();

        user.setId(entity.getId());
        user.setUserUuid(entity.getUserUuid());
        user.setCreatedAt(entity.getCreatedAt());
        user.setActivatedAt(entity.getActivatedAt());
        user.setDeletedAt(entity.getDeletedAt());
        user.setStatus(UserStatus.valueOf(entity.getStatus().name()));

        return user;
    }

    @Override
    public UserEntity toEntity(User model) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(model.getId());
        userEntity.setUserUuid(model.getUserUuid());
        userEntity.setCreatedAt(model.getCreatedAt());
        userEntity.setActivatedAt(model.getActivatedAt());
        userEntity.setDeletedAt(model.getDeletedAt());
        userEntity.setStatus(UserStatusEntity.valueOf(model.getStatus().name()));

        return userEntity;
    }
}