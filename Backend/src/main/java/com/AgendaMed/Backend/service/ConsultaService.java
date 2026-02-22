package com.AgendaMed.Backend.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.AgendaMed.Backend.repository.ConsultaRepository;
import com.AgendaMed.Backend.repository.MedicoRepository;
import com.AgendaMed.Backend.repository.PacienteRepository;
import com.AgendaMed.Backend.model.Consulta;
import com.AgendaMed.Backend.model.Medico;
import com.AgendaMed.Backend.model.Paciente;
import com.AgendaMed.Backend.model.enums.StatusConsulta;
import com.AgendaMed.Backend.dto.request.ConsultaCreateDTO;
import com.AgendaMed.Backend.dto.response.ConsultaResponseDTO;
import com.AgendaMed.Backend.dto.response.MedicoResumoDTO;
import com.AgendaMed.Backend.dto.response.PacienteResumoDTO;
import com.AgendaMed.Backend.exception.BusinessException;
import com.AgendaMed.Backend.exception.ResourceNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional
    public ConsultaResponseDTO agendarConsulta(ConsultaCreateDTO dto) {
        validarHorario(dto.dataHora());

        Medico medico = medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        if (medico.getUsuario().getAtivo())
            throw new BusinessException("O Médico deve estar Ativo");
        if (paciente.getUsuario().getAtivo())
            throw new BusinessException("O Paciente deve estar Ativo");

        if (consultaRepository.existsByMedicoIdAndDataHora(dto.medicoId(), dto.dataHora())) {
            throw new BusinessException("Já existe uma consulta para esse médico nesse horário.");
        }

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataHora(dto.dataHora());
        consulta.setStatus(StatusConsulta.AGENDADA);
        consulta = consultaRepository.save(consulta);

        return new ConsultaResponseDTO(
                consulta.getId(),
                new MedicoResumoDTO(medico.getId(), medico.getUsuario().getName(), null, medico.getCrm()),
                new PacienteResumoDTO(paciente.getId(), paciente.getUsuario().getName(), paciente.getTelefone(),
                        paciente.getUsuario().getEmail()),
                consulta.getDataHora(),
                consulta.getStatus());
    }

    private void validarHorario(LocalDateTime dataHora) {
        LocalTime inicio = LocalTime.of(8, 0);
        LocalTime fim = LocalTime.of(17, 0);

        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new BusinessException("Não é possível agendar no passado.");
        }
        if (dataHora.getSecond() != 0 || dataHora.getNano() != 0) {
            throw new BusinessException("Segundos e nanos devem ser zero.");
        }
        LocalTime hora = dataHora.toLocalTime();
        if (hora.isBefore(inicio) || hora.isAfter(fim.minusMinutes(15))) {
            throw new BusinessException("Horário fora do funcionamento (08:00-17:00).");
        }
        if (hora.getMinute() % 15 != 0) {
            throw new BusinessException("Horário deve ser múltiplo de 15 minutos.");
        }
    }
}