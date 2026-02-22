package com.AgendaMed.Backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.AgendaMed.Backend.dto.request.LoginDTO;

import com.AgendaMed.Backend.dto.response.TokenDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public TokenDTO authenticate(LoginDTO request) {

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha()));

        return new TokenDTO(
                jwtService.generateToken(authentication));
    }
}
