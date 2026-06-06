package com.clinica.application;

import com.clinica.domain.PapelUsuario;
import com.clinica.domain.Usuario;
import com.clinica.infrastructure.UsuarioEntity;
import com.clinica.infrastructure.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioUseCase {

    private final UsuarioRepository repository;

    public CadastrarUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioEntity executar(String nome, String email, String senha, PapelUsuario papel) {
        if (repository.findByEmail(email.toLowerCase()).isPresent()) {
            throw new IllegalArgumentException("Este e-mail já está em uso.");
        }

        Usuario usuarioDomain = new Usuario(nome, email, senha, papel);

        UsuarioEntity entity = new UsuarioEntity(
                usuarioDomain.getNome(),
                usuarioDomain.getEmail(),
                usuarioDomain.getSenha(),
                usuarioDomain.getPapel()
        );

        return repository.save(entity);
    }
}