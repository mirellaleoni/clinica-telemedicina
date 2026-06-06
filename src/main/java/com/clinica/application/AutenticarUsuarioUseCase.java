package com.clinica.application;

import com.clinica.infrastructure.UsuarioEntity;
import com.clinica.infrastructure.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticarUsuarioUseCase {

    private final UsuarioRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AutenticarUsuarioUseCase(UsuarioRepository repository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public String executar(String email, String senha) {
        UsuarioEntity usuario = repository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("E-mail ou senha inválidos."));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new IllegalArgumentException("E-mail ou senha inválidos.");
        }

        return tokenService.gerarToken(usuario);
    }
}