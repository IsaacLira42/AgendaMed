package com.AgendaMed.Backend.dto.response;

public record MedicoResumoDTO(
                Long id,
                String nome,
                String especialidade,
                String crm) {
}