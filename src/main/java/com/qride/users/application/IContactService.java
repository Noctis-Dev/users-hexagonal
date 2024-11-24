package com.qride.users.application;

import com.qride.users.domain.models.Contact;
import com.qride.users.application.dto.BaseResponse;
import com.qride.users.application.dto.request.ContactRequest;

import java.util.UUID;

public interface IContactService {
    BaseResponse createContact(ContactRequest contact);
    Contact find(UUID uuid);
}
