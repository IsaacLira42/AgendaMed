package com.AgendaMed.Backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgendaMed.Backend.dto.request.ConsultaCreateDTO;
import com.AgendaMed.Backend.dto.response.ConsultaResponseDTO;
import com.AgendaMed.Backend.service.ConsultaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ConsultaResponseDTO criarConsulta(@RequestBody ConsultaCreateDTO consulta) {

        return consultaService.agendarConsulta(consulta);
    }
}
