package com.clinica.infrastructure;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class ConsultaEntity {

    @Id
    private UUID id;

    @Column(name = "agendamento_id", nullable = false)
    private UUID agendamentoId;

    @Column(name = "link_video", nullable = false)
    private String linkVideochamada;

    @Column(name = "iniciada_em")
    private LocalDateTime iniciadaEm;

    @Column(name = "finalizada_em")
    private LocalDateTime finalizadaEm;

    protected ConsultaEntity() {}

    public ConsultaEntity(UUID id, UUID agendamentoId, String linkVideochamada, LocalDateTime iniciadaEm, LocalDateTime finalizadaEm) {
        this.id = id;
        this.agendamentoId = agendamentoId;
        this.linkVideochamada = linkVideochamada;
        this.iniciadaEm = iniciadaEm;
        this.finalizadaEm = finalizadaEm;
    }

    public UUID getId() { return id; }
    public UUID getAgendamentoId() { return agendamentoId; }
    public String getLinkVideochamada() { return linkVideochamada; }
    public LocalDateTime getIniciadaEm() { return iniciadaEm; }
    public LocalDateTime getFinalizadaEm() { return finalizadaEm; }

    public void setIniciadaEm(LocalDateTime iniciadaEm) { 
        this.iniciadaEm = iniciadaEm; 
    }

    public void setFinalizadaEm(LocalDateTime finalizadaEm) {
        this.finalizadaEm = finalizadaEm;
    }
}