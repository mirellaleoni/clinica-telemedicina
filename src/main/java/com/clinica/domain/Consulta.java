package com.clinica.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Consulta {
    private final UUID id;
    private final UUID agendamentoId;
    private final LinkVideochamada linkVideochamada;
    private final LocalDateTime iniciadaEm;
    private LocalDateTime finalizadaEm;

    public Consulta(UUID agendamentoId, LinkVideochamada linkVideochamada) {
        if (agendamentoId == null) {
            throw new IllegalArgumentException("O ID do agendamento é obrigatório.");
        }
        this.id = UUID.randomUUID();
        this.agendamentoId = agendamentoId;
        this.linkVideochamada = linkVideochamada;
        this.iniciadaEm = LocalDateTime.now();
    }

    public void finalizar() {
        if (this.finalizadaEm != null) {
            throw new IllegalStateException("Esta consulta já foi finalizada.");
        }
        this.finalizadaEm = LocalDateTime.now();
    }

    // Getters
    public UUID getId() { return id; }
    public UUID getAgendamentoId() { return agendamentoId; }
    public LinkVideochamada getLinkVideochamada() { return linkVideochamada; }
    public LocalDateTime getIniciadaEm() { return iniciadaEm; }
    public LocalDateTime getFinalizadaEm() { return finalizadaEm; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id.equals(((Consulta) o).id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}