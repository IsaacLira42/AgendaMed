package com.AgendaMed.Backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgendaMed.Backend.dto.request.LoginDTO;
import com.AgendaMed.Backend.dto.request.RegisterPacienteDTO;
import com.AgendaMed.Backend.dto.response.PacienteResumoDTO;
import com.AgendaMed.Backend.dto.response.TokenDTO;
import com.AgendaMed.Backend.service.AuthenticationService;
import com.AgendaMed.Backend.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final PacienteService pacienteService;

    @PostMapping("/login")
    public TokenDTO authenticate(@RequestBody LoginDTO request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/register")
    public PacienteResumoDTO register(@RequestBody RegisterPacienteDTO request) {
        return pacienteService.register(request);
    }
}