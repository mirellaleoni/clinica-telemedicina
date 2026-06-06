package com.clinica.application;

import com.clinica.infrastructure.UsuarioEntity;
import com.clinica.infrastructure.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AutenticarUsuarioUseCase {

    private final UsuarioRepository repository;
    private final TokenService tokenService; // A mágica acontece aqui!

    public AutenticarUsuarioUseCase(UsuarioRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public String executar(String email, String senha) {
        UsuarioEntity usuario = repository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("E-mail ou senha inválidos."));

        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("E-mail ou senha inválidos.");
        }

        return tokenService.gerarToken(usuario);
    }
}