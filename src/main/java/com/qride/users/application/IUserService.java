package com.qride.users.application;


import com.qride.users.domain.models.Contact;
import com.qride.users.domain.models.User;

import java.util.UUID;

public interface IUserService {

    User find(UUID userUuid);
    User create(Contact contact);

}
