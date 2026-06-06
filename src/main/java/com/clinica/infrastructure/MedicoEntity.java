package com.clinica.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.UUID;

@Entity
@Table(name = "medicos")
public class MedicoEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String crm;

    @Column(nullable = false)
    private String email;

    private String especialidade;

    private Boolean ativo;

    protected MedicoEntity() {}

    public MedicoEntity(UUID id, String nome, String cpf, String crm, String email, String especialidade, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.crm = crm;
        this.email = email;
        this.especialidade = especialidade;
        this.ativo = ativo;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getCrm() { return crm; }
    public String getEmail() { return email; }
    public String getEspecialidade() { return especialidade; }
    public Boolean getAtivo() { return ativo; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}