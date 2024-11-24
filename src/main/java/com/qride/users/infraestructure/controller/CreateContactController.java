package com.qride.users.infraestructure.controller;

import com.qride.users.application.IContactService;
import com.qride.users.application.dto.BaseResponse;
import com.qride.users.application.dto.request.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contact")
public class CreateContactController {

    @Autowired
    private IContactService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody ContactRequest request) {
        return service.createContact(request).apply();
    }

}
