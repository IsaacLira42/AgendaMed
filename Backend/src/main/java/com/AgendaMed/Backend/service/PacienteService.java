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

import org.springframework.transaction.annotation.Transactional;
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
                usuario.getPaciente().getTelefone(),
                usuario.getEmail());
    }

    @Transactional(readOnly = true)
    public AgendaDTO getAgenda() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        if (usuario.getPaciente() == null) {
            throw new ResourceNotFoundException("Paciente não encontrado");
        }

        Long pacienteId = usuario.getPaciente().getId();

        List<Consulta> consultasFuturas = consultaRepository
                .buscarAgendaPaciente(
                        pacienteId,
                        LocalDateTime.now());

        // Caso 1: Não tem consultas futuras
        if (consultasFuturas.isEmpty()) {
            return new AgendaDTO(null, List.of());
        }

        // Caso 2: Tem consultas
        ConsultaResponseDTO proximaConsulta = ConsultaMapper.toResponseDTO(consultasFuturas.get(0));

        List<ConsultaResponseDTO> futuras = consultasFuturas.stream()
                .skip(1)
                .map(ConsultaMapper::toResponseDTO)
                .toList();

        return new AgendaDTO(proximaConsulta, futuras);
    }
}