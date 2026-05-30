package com.clinica.domain;

import java.util.UUID;

public class Paciente {
    private String nome;
    private CPF cpf;
    private Email email;

    public Paciente(String nome, CPF cpf, Email email) {
        if( nome == null || nome.trim().isEmpty() ) {
            throw new IllegalArgumentException("O nome do paciente é obrigatório.");
        }
        if( cpf == null ) {
            throw new IllegalArgumentException("O CPF do paciente é obrigatório.");
        }
        if ( email == null ) {
            throw new IllegalArgumentException("O email do paciente é obrigatório.");
        }

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public CPF getCpf() { return cpf; }
    public Email getEmail() { return email; }

    public void alterarNome(String novoNome) {
        if( novoNome == null || novoNome.trim().isEmpty() ) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = novoNome;
    }

    public void alterarEmail(Email novoEmail) {
        if ( novoEmail == null ) {
            throw new IllegalArgumentException("O email não pode ser vazio.");
        }
        this.email = novoEmail;
    }

    @Override
    public String equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return id.equals(paciente.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}