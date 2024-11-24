package com.qride.users.application;

import com.qride.users.application.dto.BaseResponse;
import com.qride.users.application.dto.request.CredentialRequest;

import java.util.UUID;

public interface IAuthService {

    BaseResponse signUp(CredentialRequest request);
    BaseResponse verifyToken(String token, UUID credentialUuid);

}
