package com.AgendaMed.Backend.dto.response;

public record PacienteResumoDTO(
        Long id,
        String nome,
        String telefone,
        String email) {
}