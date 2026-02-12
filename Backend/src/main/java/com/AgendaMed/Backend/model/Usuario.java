package com.AgendaMed.Backend.model;

import java.time.LocalDateTime;

import com.AgendaMed.Backend.model.enums.TipoUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @NotBlank
    private Boolean ativo = true;

    @NotBlank
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Paciente paciente;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Medico medico;
}