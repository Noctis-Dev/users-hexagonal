package com.qride.users.application.impl;

import com.qride.users.application.IUserService;
import com.qride.users.domain.models.Contact;
import com.qride.users.domain.models.User;
import com.qride.users.domain.models.enums.UserRole;
import com.qride.users.domain.models.enums.UserStatus;
import com.qride.users.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public User find(UUID userUuid) {
        return repository.find(userUuid);
    }

    @Override
    public User create(Contact contact) {
        User user = new User();

        user.setUserUuid(UUID.randomUUID());
        user.setCreatedAt(LocalDate.now());
        user.setStatus(UserStatus.INACTIVE);
        user.setContact(contact);
        user.setRole(UserRole.USER);

        return repository.create(user);
    }
}
