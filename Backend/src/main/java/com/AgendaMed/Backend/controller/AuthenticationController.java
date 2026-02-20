package com.AgendaMed.Backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AgendaMed.Backend.dto.request.LoginDTO;
import com.AgendaMed.Backend.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("auth")
    public String authenticate(@RequestBody LoginDTO request) {
        return authenticationService.authenticate(request);
    }
}
