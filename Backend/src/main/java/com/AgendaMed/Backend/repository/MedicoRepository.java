package com.AgendaMed.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.AgendaMed.Backend.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
