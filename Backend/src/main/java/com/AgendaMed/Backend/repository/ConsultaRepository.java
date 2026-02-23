package com.AgendaMed.Backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.AgendaMed.Backend.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);

	List<Consulta> findByMedicoIdAndDataHoraBetween(Long medicoId, LocalDateTime inicio, LocalDateTime fim);

	List<Consulta> findByPacienteIdAndDataHoraAfterOrderByDataHoraAsc(
			Long pacienteId,
			LocalDateTime dataHora);
}
