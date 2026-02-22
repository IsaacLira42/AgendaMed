package com.AgendaMed.Backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Table(name = "paciente")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_fk", nullable = false, unique = true)
    private Usuario usuario;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String telefone;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Consulta> consultas = new ArrayList<>();

    private Paciente(String cpf, String telefone) {
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public static Paciente criar(String cpf, String telefone) {
        return new Paciente(cpf, telefone);
    }

    void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}