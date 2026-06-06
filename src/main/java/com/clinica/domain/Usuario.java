package com.clinica.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Usuario {

    private final UUID id;
    private String nome;
    private String email;
    private String senha;
    private PapelUsuario papel;
    private final LocalDateTime criadoEm;

    public Usuario(String nome, String email, String senha, PapelUsuario papel) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário é obrigatório.");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Um e-mail válido é obrigatório.");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }
        if (papel == null) {
            throw new IllegalArgumentException("O papel (perfil) do usuário é obrigatório.");
        }

        this.id = UUID.randomUUID();
        this.nome = nome.trim();
        this.email = email.trim().toLowerCase(); 
        this.senha = senha;
        this.papel = papel;
        this.criadoEm = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public PapelUsuario getPapel() { return papel; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void alterarSenha(String novaSenha) {
        if (novaSenha == null || novaSenha.trim().isEmpty()) {
            throw new IllegalArgumentException("A nova senha não pode ser vazia.");
        }
        this.senha = novaSenha;
    }
}