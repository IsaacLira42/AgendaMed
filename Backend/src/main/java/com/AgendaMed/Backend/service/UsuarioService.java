package com.AgendaMed.Backend.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.AgendaMed.Backend.dto.response.UsuarioResumoDTO;
import com.AgendaMed.Backend.exception.ResourceNotFoundException;
import com.AgendaMed.Backend.model.Usuario;
import com.AgendaMed.Backend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioResumoDTO getUsuarioResumoByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return mapToDTO(usuario);
    }

    public UsuarioResumoDTO getUsuarioResumo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getUsuarioResumoByEmail(authentication.getName());
    }

    private UsuarioResumoDTO mapToDTO(Usuario usuario) {
        return new UsuarioResumoDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getTipo().name());
    }
}