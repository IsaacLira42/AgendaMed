package com.AgendaMed.Backend.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.AgendaMed.Backend.dto.response.UsuarioResumoDTO;
import com.AgendaMed.Backend.exception.ResourceNotFoundException;
import com.AgendaMed.Backend.model.Usuario;
import com.AgendaMed.Backend.repository.UsuarioRepository;
import com.AgendaMed.Backend.security.UserAuthenticated;

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
        Object principal = authentication.getPrincipal();
        if (principal instanceof Jwt jwt) {
            return mapFromJwt(jwt);
        }
        if (principal instanceof UserAuthenticated userAuthenticated) {
            return mapToDTO(userAuthenticated.getUsuario());
        }
        return getUsuarioResumoByEmail(authentication.getName());
    }

    private UsuarioResumoDTO mapFromJwt(Jwt jwt) {
        Number idClaim = jwt.getClaim("user_id");
        Long id = idClaim != null ? idClaim.longValue() : null;
        String tipo = jwt.getClaim("user_type");
        return new UsuarioResumoDTO(
                id,
                jwt.getClaim("user_name"),
                jwt.getSubject(),
                tipo);
    }

    private UsuarioResumoDTO mapToDTO(Usuario usuario) {
        return new UsuarioResumoDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getTipo().name());
    }
}