package com.AgendaMed.Backend.dto.response;

import java.util.List;

// Exclusivo para a resposta da agenda do paciente, contendo a consulta atual e as próximas consultas agendadas.
public record AgendaDTO(
                ConsultaResponseDTO consulta,
                List<ConsultaResponseDTO> proximasConsultas) {
}
