package com.AgendaMed.Backend.service;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.AgendaMed.Backend.security.UserAuthenticated;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder encoder;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        Long expiry = 3600L;

        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claimsBuilder = JwtClaimsSet.builder()
                .issuer("spring-security-jwt")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scopes);

        if (authentication.getPrincipal() instanceof UserAuthenticated userAuthenticated) {
            var usuario = userAuthenticated.getUsuario();
            claimsBuilder.claim("user_id", usuario.getId())
                    .claim("user_name", usuario.getName())
                    .claim("user_type", usuario.getTipo().name());
        }

        var claims = claimsBuilder.build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
