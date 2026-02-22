package com.AgendaMed.Backend.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AgendaMed.Backend.dto.request.RegisterPacienteDTO;
import com.AgendaMed.Backend.dto.response.PacienteResumoDTO;
import com.AgendaMed.Backend.exception.BusinessException;
import com.AgendaMed.Backend.model.Paciente;
import com.AgendaMed.Backend.model.Usuario;
import com.AgendaMed.Backend.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {

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
}