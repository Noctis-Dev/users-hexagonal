package com.qride.users.application.impl;

import com.qride.users.application.IContactService;
import com.qride.users.domain.broker.IMessageProducer;
import com.qride.users.domain.models.Contact;
import com.qride.users.domain.repository.IContactRepository;
import com.qride.users.application.dto.BaseResponse;
import com.qride.users.application.dto.request.ContactRequest;
import com.qride.users.application.dto.response.ContactResponse;
import com.qride.users.application.factory.EventFactory;
import com.qride.users.utils.ThreadsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private IContactRepository repository;

    @Autowired
    private IMessageProducer messageProducer;

    @Override
    public BaseResponse createContact(ContactRequest contact) {
        Contact savedContact = repository.create(toContact(contact));

        ThreadsUtil.runTask(() -> {
            EventFactory factory = EventFactory.builder()
                    .type(contact.type())
                    .subject("Welcome to SCI-ALL!")
                    .message("This is a welcome message we are exited for your arrive")
                    .email(savedContact.getEmail())
                    .phoneNumber(savedContact.getPhoneNumber())
                    .producer(messageProducer).build();

            factory.getNotification().send();
        });

        return BaseResponse.builder()
                .data(toContactResponse(savedContact))
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(200).build();
    }

    @Override
    public Contact find(UUID uuid) {
        return repository.find(uuid);
    }

    private Contact toContact(ContactRequest request) {
        Contact contact = new Contact();

        contact.setContactUuid(UUID.randomUUID());
        contact.setPhoneNumber(request.phoneNumber());
        contact.setEmail(request.email());
        contact.setCreatedAt(LocalDate.now());
        contact.setName(request.name());

        return contact;
    }

    private ContactResponse toContactResponse(Contact contact) {
        return new ContactResponse(
                contact.getContactUuid(),
                contact.getPhoneNumber(),
                contact.getEmail(),
                contact.getCreatedAt()
        );
    }
}
