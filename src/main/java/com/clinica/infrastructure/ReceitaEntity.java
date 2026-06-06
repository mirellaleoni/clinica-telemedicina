package com.clinica.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "receitas")
public class ReceitaEntity {

    @Id
    private UUID id;

    @Column(name = "consulta_id", nullable = false)
    private UUID consultaId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String medicamentos;

    @Column(columnDefinition = "TEXT")
    private String instrucoes;

    @Column(name = "emitida_em", nullable = false, updatable = false)
    private LocalDateTime emitidaEm;

    protected ReceitaEntity() {}

    public ReceitaEntity(UUID id, UUID consultaId, String medicamentos, String instrucoes, LocalDateTime emitidaEm) {
        this.id = id;
        this.consultaId = consultaId;
        this.medicamentos = medicamentos;
        this.instrucoes = instrucoes;
        this.emitidaEm = emitidaEm;
    }

    public UUID getId() { return id; }
    public UUID getConsultaId() { return consultaId; }
    public String getMedicamentos() { return medicamentos; }
    public String getInstrucoes() { return instrucoes; }
    public LocalDateTime getEmitidaEm() { return emitidaEm; }

    public void setMedicamentos(String medicamentos) { this.medicamentos = medicamentos; }
    public void setInstrucoes(String instrucoes) { this.instrucoes = instrucoes; }
}