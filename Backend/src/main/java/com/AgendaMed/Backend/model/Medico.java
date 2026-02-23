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
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Table(name = "medico")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_fk", nullable = false, unique = true)
    private Usuario usuario;

    @Column(nullable = false, unique = true)
    private String crm;

    @NotBlank
    @Column(nullable = false)
    private String especialidade;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas = new ArrayList<>();

    private Medico(String crm) {
        this.crm = normalizarCrm(crm);
    }

    public static Medico criar(String crm) {
        return new Medico(crm);
    }

    void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private String normalizarCrm(String crm) {
        return crm.trim().toUpperCase();
    }
}