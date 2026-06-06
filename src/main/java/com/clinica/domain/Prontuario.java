package com.clinica.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Prontuario {

    private final UUID id;
    private final UUID consultaId;
    private String observacoes;
    private String prescricao;
    private final LocalDateTime criadoEm;

    public Prontuario(UUID consultaId, String observacoes, String prescricao) {
        if (consultaId == null) {
            throw new IllegalArgumentException("O ID da consulta é obrigatório.");
        }
        if (observacoes == null || observacoes.trim().isEmpty()) {
            throw new IllegalArgumentException("As observações do prontuário são obrigatórias.");
        }

        this.id = UUID.randomUUID();
        this.consultaId = consultaId;
        this.observacoes = observacoes.trim();
        this.prescricao = prescricao != null ? prescricao.trim() : null;
        this.criadoEm = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public UUID getConsultaId() { return consultaId; }
    public String getObservacoes() { return observacoes; }
    public String getPrescricao() { return prescricao; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void adicionarEvolucao(String novasObservacoes) {
        if (novasObservacoes == null || novasObservacoes.trim().isEmpty()) {
            throw new IllegalArgumentException("As novas observações não podem ser vazias.");
        }
        this.observacoes = this.observacoes + "\n---\n" + novasObservacoes.trim();
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