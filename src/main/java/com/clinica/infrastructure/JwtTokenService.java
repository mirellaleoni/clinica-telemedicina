package com.clinica.infrastructure;

import com.clinica.application.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JwtTokenService implements TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String gerarToken(UsuarioEntity usuario) {
        try {
            return Jwts.builder()
                    .issuer("clinica-telemedicina")
                    .subject(usuario.getEmail())
                    .claim("id", usuario.getId().toString())
                    .claim("role", usuario.getPapel().name())
                    .expiration(Date.from(gerarDataExpiracao()))
                    .signWith(getSigningKey())
                    .compact();
        } catch (Exception exception) {
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}