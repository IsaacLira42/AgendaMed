package com.AgendaMed.Backend.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private TipoUsuario tipo;

    @Column(nullable = false)
    private Boolean ativo;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Paciente paciente;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Medico medico;

    private Usuario(String name, String email, String senhaHash, TipoUsuario tipo) {
        this.name = name.trim();
        this.email = email.trim().toLowerCase();
        this.senhaHash = senhaHash;
        this.tipo = tipo;
        this.ativo = true;
    }

    public static Usuario criarPaciente(String name, String email, String senhaHash) {
        return new Usuario(name, email, senhaHash, TipoUsuario.PACIENTE);
    }

    public static Usuario criarMedico(String name, String email, String senhaHash) {
        return new Usuario(name, email, senhaHash, TipoUsuario.MEDICO);
    }

    public void associarPaciente(Paciente paciente) {
        if (this.tipo != TipoUsuario.PACIENTE) {
            throw new IllegalStateException("Usuário não é do tipo PACIENTE");
        }
        this.paciente = paciente;
        paciente.setUsuario(this);
    }

    public void associarMedico(Medico medico) {
        if (this.tipo != TipoUsuario.MEDICO) {
            throw new IllegalStateException("Usuário não é do tipo MEDICO");
        }
        this.medico = medico;
        medico.setUsuario(this);
    }
}