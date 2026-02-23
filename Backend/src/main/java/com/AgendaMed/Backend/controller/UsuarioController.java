package com.AgendaMed.Backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgendaMed.Backend.dto.response.UsuarioResumoDTO;
import com.AgendaMed.Backend.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/me")
    public UsuarioResumoDTO getUsuarioResumo() {
        return usuarioService.getUsuarioResumo();
    }
}
