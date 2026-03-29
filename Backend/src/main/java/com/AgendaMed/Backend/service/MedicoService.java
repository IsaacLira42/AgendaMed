package com.AgendaMed.Backend.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AgendaMed.Backend.dto.request.RegisterMedicoDTO;
import com.AgendaMed.Backend.dto.response.MedicoResumoDTO;
import com.AgendaMed.Backend.exception.BusinessException;
import com.AgendaMed.Backend.model.Medico;
import com.AgendaMed.Backend.model.Usuario;
import com.AgendaMed.Backend.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MedicoResumoDTO register(RegisterMedicoDTO request) {

        String senhaHash = passwordEncoder.encode(request.senha());

        Usuario usuario = Usuario.criarMedico(
                request.name(), request.email(), senhaHash);

        Medico medico = Medico.criar(request.crm());

        usuario.associarMedico(medico);

        try {
            usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessException("Email ou CPF já cadastrado");
        }

        return new MedicoResumoDTO(
                usuario.getMedico().getId(),
                usuario.getName(),
                usuario.getMedico().getEspecialidade(),
                usuario.getMedico().getCrm());
    }
}
