package com.qride.users.infraestructure.controller;

import com.qride.users.application.IAuthService;
import com.qride.users.application.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("verify")
public class VerifyCredentialController {

    @Autowired
    private IAuthService service;

    @GetMapping("credential/{credentialUuid}")
    public ResponseEntity<BaseResponse> verifyCredential(@PathVariable UUID credentialUuid,
                                                         @RequestParam String token) {
        return service.verifyToken(token, credentialUuid).apply();
    }

}
