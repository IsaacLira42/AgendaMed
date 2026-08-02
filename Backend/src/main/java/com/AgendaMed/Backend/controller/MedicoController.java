package com.AgendaMed.Backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AgendaMed.Backend.dto.response.HorarioDisponivelDTO;
import com.AgendaMed.Backend.service.ConsultaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/medicos")
public class MedicoController {
    private final ConsultaService consultaService;

    @GetMapping("/{medicoId}/horarios")
    public ResponseEntity<List<HorarioDisponivelDTO>> listarHorariosDisponiveis(
            @PathVariable Long medicoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        return ResponseEntity.ok(consultaService.listarHorariosDisponiveis(medicoId, data));
    }
}
