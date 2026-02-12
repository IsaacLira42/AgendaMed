package com.AgendaMed.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.AgendaMed.Backend.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}