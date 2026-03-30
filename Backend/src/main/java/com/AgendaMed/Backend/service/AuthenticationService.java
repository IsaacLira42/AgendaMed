package com.AgendaMed.Backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.AgendaMed.Backend.dto.request.LoginDTO;
import com.AgendaMed.Backend.dto.response.UsuarioResumoDTO;
import com.AgendaMed.Backend.security.UserAuthenticated;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationResult authenticate(LoginDTO request) {

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha()));

        return new AuthenticationResult(
                jwtService.generateToken(authentication),
                mapToUsuarioResumo(authentication));
    }

    private UsuarioResumoDTO mapToUsuarioResumo(Authentication authentication) {
        if (authentication.getPrincipal() instanceof UserAuthenticated userAuthenticated) {
            var usuario = userAuthenticated.getUsuario();
            return new UsuarioResumoDTO(
                    usuario.getId(),
                    usuario.getName(),
                    usuario.getEmail(),
                    usuario.getTipo().name());
        }
        throw new IllegalStateException("Não foi possível extrair dados do usuário autenticado");
    }

    public record AuthenticationResult(String token, UsuarioResumoDTO usuario) {
    }
}
