package com.AgendaMed.Backend.dto.request;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull String email,

        @NotNull String senha) {
}
