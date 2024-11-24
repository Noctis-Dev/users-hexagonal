package com.qride.users.domain.repository;

import jakarta.validation.Valid;
import com.qride.users.domain.models.Contact;

import java.util.UUID;

public interface IContactRepository {
    Contact create(@Valid Contact contact);
    Contact find(UUID uuid);
}
