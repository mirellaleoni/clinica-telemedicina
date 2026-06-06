package com.clinica.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Receita {
    private final UUID id;
    private final UUID consultaId;
    private String medicamentos;
    private String instrucoes;
    private final LocalDateTime emitidaEm;

    public Receita(UUID consultaId, String medicamentos, String instrucoes) {
        if (consultaId == null) {
            throw new IllegalArgumentException("O ID da consulta é obrigatório.");
        }
        if (medicamentos == null || medicamentos.trim().isEmpty()) {
            throw new IllegalArgumentException("Os medicamentos não podem estar vazios.");
        }
        if (instrucoes == null || instrucoes.trim().isEmpty()) {
            throw new IllegalArgumentException("As instruções não podem estar vazias.");
        }

        this.id = UUID.randomUUID();
        this.consultaId = consultaId;
        this.medicamentos = medicamentos;
        this.instrucoes = instrucoes;
        this.emitidaEm = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public UUID getConsultaId() { return consultaId; }
    public String getMedicamentos() { return medicamentos; }
    public String getInstrucoes() { return instrucoes; }
    public LocalDateTime getEmitidaEm() { return emitidaEm; }
}