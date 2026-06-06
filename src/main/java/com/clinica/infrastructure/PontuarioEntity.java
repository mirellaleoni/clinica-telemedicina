package com.clinica.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prontuarios")
public class PontuarioEntity {

    @Id
    private UUID id;

    @Column(name = "consulta_id", nullable = false)
    private UUID consultaId;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(columnDefinition = "TEXT")
    private String prescricao;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    protected PontuarioEntity() {}

    public PontuarioEntity(UUID id, UUID consultaId, String observacoes, String prescricao, LocalDateTime criadoEm) {
        this.id = id;
        this.consultaId = consultaId;
        this.observacoes = observacoes;
        this.prescricao = prescricao;
        this.criadoEm = criadoEm;
    }

    public UUID getId() { return id; }
    public UUID getConsultaId() { return consultaId; }
    public String getObservacoes() { return observacoes; }
    public String getPrescricao() { return prescricao; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public void setPrescricao(String prescricao) { this.prescricao = prescricao; }
}