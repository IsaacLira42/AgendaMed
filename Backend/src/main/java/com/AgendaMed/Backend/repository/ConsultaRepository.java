package com.AgendaMed.Backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AgendaMed.Backend.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);

	List<Consulta> findByMedicoIdAndDataHoraBetween(Long medicoId, LocalDateTime inicio, LocalDateTime fim);

	// List<Consulta> findByPacienteIdAndDataHoraAfterOrderByDataHoraAsc(
	// Long pacienteId,
	// LocalDateTime dataHora);

	@Query("""
			SELECT c FROM Consulta c
			JOIN FETCH c.medico m
			JOIN FETCH m.usuario
			JOIN FETCH c.paciente p
			JOIN FETCH p.usuario
			WHERE p.id = :pacienteId
			AND c.dataHora > :agora
			ORDER BY c.dataHora ASC
			""")
	List<Consulta> buscarAgendaPaciente(
			@Param("pacienteId") Long pacienteId,
			@Param("agora") LocalDateTime agora);

	// Busca todas as consultas do paciente
	List<Consulta> findByPacienteIdOrderByDataHoraDesc(Long pacienteId);
}
