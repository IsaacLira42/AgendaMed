package com.AgendaMed.Backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterMedicoDTO(
        @NotBlank(message = "O nome é obrigatório") String name,

        @NotBlank(message = "O e-mail é obrigatório") @Email(message = "Formato de e-mail inválido") String email,

        @NotBlank(message = "A senha é obrigatória") @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres") String senha,

        @NotBlank(message = "O CRM é obrigatório") String crm) {
}