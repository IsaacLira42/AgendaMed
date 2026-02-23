package com.AgendaMed.Backend.dto.response;

public record UsuarioResumoDTO(
        Long id,
        String nome,
        String email,
        String tipo) {
}