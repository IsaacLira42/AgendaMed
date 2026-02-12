package com.AgendaMed.Backend.dto.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ConsultaCreateDTO(
                @NotNull(message = "ID do médico é obrigatório") Long medicoId,

                @NotNull(message = "ID do paciente é obrigatório") Long pacienteId,

                @NotNull(message = "Data e hora são obrigatórias") @Future(message = "A consulta deve ser agendada para uma data futura") LocalDateTime dataHora) {
}