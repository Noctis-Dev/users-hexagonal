package com.qride.users.application.impl;

import com.qride.users.application.*;
import com.qride.users.application.dto.request.LogInRequest;
import com.qride.users.application.dto.response.LogInResponse;
import com.qride.users.domain.broker.IMessageProducer;
import com.qride.users.domain.models.Contact;
import com.qride.users.domain.models.Credential;
import com.qride.users.domain.models.Token;
import com.qride.users.domain.models.User;
import com.qride.users.domain.models.enums.UserStatus;
import com.qride.users.application.dto.BaseResponse;
import com.qride.users.application.dto.request.CredentialRequest;
import com.qride.users.application.dto.response.SignUpResponse;
import com.qride.users.application.dto.response.UserResponse;
import com.qride.users.application.factory.EventFactory;
import com.qride.users.utils.ThreadsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private ICredentialService credentialService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IContactService contactService;

    @Autowired
    private IMessageProducer messageProducer;

    @Override
    public BaseResponse signUp(CredentialRequest request) {
        Contact contact = contactService.find(request.contactUuid());
        Token savedToken = tokenService.createToken();

        ThreadsUtil.runTask(() -> {
            EventFactory factory = EventFactory.builder()
                    .type(request.type())
                    .subject("Verification code QRide")
                    .message("Codigo de verificacion: " + savedToken.getToken())
                    .email(contact.getEmail())
                    .phoneNumber(contact.getPhoneNumber())
                    .producer(messageProducer).build();

            factory.getNotification().send();
        });

        Credential savedCredential = credentialService.create(request, savedToken, contact);
        User savedUser = userService.create(contact);

        SignUpResponse response = new SignUpResponse();
        response.setUserUuid(savedUser.getUserUuid());
        response.setContactUuid(contact.getContactUuid());
        response.setCredentialUuid(savedCredential.getCredentialUuid());

        return BaseResponse.builder()
                .data(response)
                .message("The user account was created successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(200).build();
    }

    @Override
    public  BaseResponse logIn(LogInRequest request) {
        Credential credential;
        if (request.phoneNumber() == null || request.phoneNumber().isEmpty()) {
            credential = credentialService.find(request.email());
        } else {
            credential = credentialService.find(request.phoneNumber());
        }
        Contact contact = credential.getContact();

        if (!credential.getPassword().equals(request.password())) {
            throw new RuntimeException();
        }

        LogInResponse response = new LogInResponse();
        response.setUser(contact.getName());

        return BaseResponse.builder()
                .data(response)
                .message("User logged in successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(200).build();
    }

    @Override
    public BaseResponse verifyToken(String token, UUID credentialUuid) {
        Credential credential = credentialService.find(credentialUuid);
        User user = credential.getContact().getUser();

        if (!credential.getToken().getToken().equals(token)) {
            throw new RuntimeException();
        }

        user.setActivatedAt(LocalDate.now());
        user.setStatus(UserStatus.ACTIVE);
        credential.setVerifiedAt(LocalDate.now());

        return BaseResponse.builder()
                .data(toUserResponse(user))
                .message("Credential verified successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .status(200).build();
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(
            user.getUserUuid(),
            user.getCreatedAt(),
            user.getActivatedAt(),
            user.getDeletedAt(),
            user.getStatus()
        );
    }

}
