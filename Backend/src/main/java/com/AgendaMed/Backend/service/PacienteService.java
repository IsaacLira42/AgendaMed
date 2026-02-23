package com.AgendaMed.Backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AgendaMed.Backend.dto.request.RegisterPacienteDTO;
import com.AgendaMed.Backend.dto.response.AgendaDTO;
import com.AgendaMed.Backend.dto.response.ConsultaResponseDTO;
import com.AgendaMed.Backend.dto.response.PacienteResumoDTO;
import com.AgendaMed.Backend.exception.BusinessException;
import com.AgendaMed.Backend.exception.ResourceNotFoundException;
import com.AgendaMed.Backend.mapper.ConsultaMapper;
import com.AgendaMed.Backend.model.Consulta;
import com.AgendaMed.Backend.model.Paciente;
import com.AgendaMed.Backend.model.Usuario;
import com.AgendaMed.Backend.repository.ConsultaRepository;
import com.AgendaMed.Backend.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final ConsultaRepository consultaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PacienteResumoDTO register(RegisterPacienteDTO request) {

        String senhaHash = passwordEncoder.encode(request.senha());

        Usuario usuario = Usuario.criarPaciente(
                request.name(),
                request.email(),
                senhaHash);

        Paciente paciente = Paciente.criar(
                request.cpf(),
                request.telefone());

        usuario.associarPaciente(paciente);

        try {
            usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessException("Email ou CPF já cadastrado");
        }

        return new PacienteResumoDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getPaciente().getTelefone());
    }

    public AgendaDTO getAgenda() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long pacienteId = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"))
                .getPaciente().getId();

        List<Consulta> consultasFuturas = consultaRepository
                .findByPacienteIdAndDataHoraAfterOrderByDataHoraAsc(
                        pacienteId,
                        LocalDateTime.now());

        if (consultasFuturas.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Não foi encontrada nenhuma consulta futura para esse paciente");
        }

        // próxima consulta
        ConsultaResponseDTO proximaConsultaDTO = ConsultaMapper.toResponseDTO(consultasFuturas.get(0));

        // demais consultas futuras (sem duplicar a primeira)
        List<ConsultaResponseDTO> futurasDTO = consultasFuturas.stream()
                .skip(1)
                .map(ConsultaMapper::toResponseDTO)
                .toList();

        return new AgendaDTO(proximaConsultaDTO, futurasDTO);
    }
}