package com.clinica.domain;

import java.util.UUID;

public class Paciente {
    private final UUID id;
    private String nome;
    private final CPF cpf;

    public Paciente(String nome, CPF cpf) {
        if( nome == null || nome.trim().isEmpty() ) {
            throw new IllegalArgumentException("O nome do paciente é obrigatório.");
        }
        if( cpf == null ) {
            throw new IllegalArgumentException("O CPF do paciente é obrigatório.");
        }

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.cpf = cpf;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public CPF getCpf() { return cpf; }

    public void alterarNome(String novoNome) {
        if( novoNome == null || novoNome.trim().isEmpty() ) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = novoNome;
    }

    @Override
    public boolean equals(Object o) {
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