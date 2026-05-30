package com.clinica.domain;

import java.util.UUID;

public class Medico {

    private final UUID id;
    private String nome;
    private final CPF cpf;
    private final CRM crm;
    private Email email;

    public Medico(String nome, CPF cpf, CRM crm, Email email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do médico é obrigatório.");
        }
        if (cpf == null) {
            throw new IllegalArgumentException("O CPF do médico é obrigatório.");
        }
        if (crm == null) {
            throw new IllegalArgumentException("O CRM do médico é obrigatório.");
        }
        if (email == null) {
            throw new IllegalArgumentException("O email do médico é obrigatório.");
        }

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.cpf = cpf;
        this.crm = crm;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public CPF getCpf() { return cpf; }
    public CRM getCrm() { return crm; }
    public Email getEmail() { return email; }

    public void alterarNome(String novoNome) {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = novoNome;
    }

    public void alterarEmail(Email novoEmail) {
        if (novoEmail == null) {
            throw new IllegalArgumentException("O email não pode ser vazio.");
        }
        this.email = novoEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return id.equals(medico.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}