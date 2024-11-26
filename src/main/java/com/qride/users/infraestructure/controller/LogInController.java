package com.qride.users.infraestructure.controller;

import com.qride.users.application.IAuthService;
import com.qride.users.application.dto.BaseResponse;
import com.qride.users.application.dto.request.LogInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login/passive")
public class LogInController {

    @Autowired
    private IAuthService service;

    @PostMapping
    public ResponseEntity<BaseResponse> login(@RequestBody LogInRequest request) {
        return service.logIn(request).apply();
    }

}
