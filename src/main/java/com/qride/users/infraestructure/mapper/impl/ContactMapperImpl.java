package com.qride.users.infraestructure.mapper.impl;

import com.qride.users.domain.models.Contact;
import com.qride.users.infraestructure.entities.ContactEntity;
import com.qride.users.infraestructure.mapper.IContactMapper;
import com.qride.users.infraestructure.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ContactMapperImpl implements IContactMapper {

    @Autowired
    @Qualifier("contactUserMapperImpl")
    private IUserMapper userMapper;

    @Override
    public Contact toDomain(ContactEntity entity) {
        Contact contact = new Contact();

        contact.setId(entity.getId());
        contact.setContactUuid(entity.getContactUuid());
        contact.setPhoneNumber(entity.getPhoneNumber());
        contact.setEmail(entity.getEmail());
        contact.setCreatedAt(entity.getCreatedAt());

        if (entity.getUserEntity() == null) {
            return contact;
        }

        contact.setUser(userMapper.toDomain(entity.getUserEntity()));
        return contact;
    }

    @Override
    public ContactEntity toEntity(Contact model) {
        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setId(model.getId());
        contactEntity.setContactUuid(model.getContactUuid());
        contactEntity.setPhoneNumber(model.getPhoneNumber());
        contactEntity.setEmail(model.getEmail());
        contactEntity.setCreatedAt(model.getCreatedAt());

        if (model.getUser() == null) {
            return contactEntity;
        }

        contactEntity.setUserEntity(userMapper.toEntity(model.getUser()));
        return contactEntity;
    }
}
