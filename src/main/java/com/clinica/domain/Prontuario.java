package com.clinica.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Prontuario {

    private final UUID id;
    private final UUID pacienteId;
    private final UUID medicoId;
    private String descricao;
    private final LocalDateTime criadoEm;

    public Prontuario(UUID pacienteId, UUID medicoId, String descricao) {
        if (pacienteId == null) {
            throw new IllegalArgumentException("Paciente ID não pode ser nulo");
        }
        if (medicoId == null) {
            throw new IllegalArgumentException("Médico ID não pode ser nulo");
        }
        if (descricao == null) {
            throw new IllegalArgumentException("Descrição não pode ser nula");
        }

        this.id = UUID.randomUUID();
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.descricao = descricao.trim();
        this.criadoEm = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public UUID getPacienteId() { return pacienteId; }
    public UUID getMedicoId() { return medicoId; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void adicionarEvolucao(String novaDescricao) {
        if (novaDescricao == null || novaDescricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A nova descrição não pode ser vazia.");
        }
        this.descricao = this.descricao + "\n---\n" + novaDescricao.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prontuario that = (Prontuario) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}