package com.AgendaMed.Backend.dto.response;

import java.time.LocalTime;

public record HorarioDisponivelDTO(
        LocalTime horario,
        boolean disponivel
) {
}