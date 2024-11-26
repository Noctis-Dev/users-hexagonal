package com.qride.users.application;

import com.qride.users.application.dto.BaseResponse;
import com.qride.users.application.dto.request.CredentialRequest;
import com.qride.users.application.dto.request.LogInRequest;

import java.util.UUID;

public interface IAuthService {

    BaseResponse signUp(CredentialRequest request);
    BaseResponse logIn(LogInRequest request);
    BaseResponse verifyToken(String token, UUID credentialUuid);

}
