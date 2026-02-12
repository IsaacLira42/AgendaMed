package com.AgendaMed.Backend.dto.response;

import java.time.LocalDateTime;

import com.AgendaMed.Backend.model.enums.StatusConsulta;

public record ConsultaResponseDTO(
        Long id,
        MedicoResumoDTO medico,
        PacienteResumoDTO paciente,
        LocalDateTime dataHora,
        StatusConsulta status) {
}
