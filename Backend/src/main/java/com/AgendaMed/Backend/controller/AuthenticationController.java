package com.AgendaMed.Backend.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    public void authenticate(@RequestBody LoginDTO request, HttpServletResponse response) {
        TokenDTO tokenDto = authenticationService.authenticate(request);
        
        Cookie cookie = new Cookie("AUTH-TOKEN", tokenDto.token());
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // Em produção, usar true (HTTPS)
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        
        response.addCookie(cookie);
    }

    @PostMapping("/register")
    public PacienteResumoDTO register(@RequestBody RegisterPacienteDTO request) {
        return pacienteService.register(request);
    }
}