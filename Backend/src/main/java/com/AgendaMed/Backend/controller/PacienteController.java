package com.AgendaMed.Backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgendaMed.Backend.dto.response.AgendaDTO;
import com.AgendaMed.Backend.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping("/agenda")
    public AgendaDTO getAgenda() {
        return pacienteService.getAgenda();
    }
}
