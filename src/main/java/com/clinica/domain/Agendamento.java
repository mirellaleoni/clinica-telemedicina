package com.clinica.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Agendamento {

    private final UUID id;
    private final UUID pacienteId;
    private final UUID medicoId;
    private final LocalDateTime dataHora;
    private final String tipo;
    private StatusAgendamento status;

    public Agendamento(UUID pacienteId, UUID medicoId, LocalDateTime dataHora, String tipo) {
        if (pacienteId == null) {
            throw new IllegalArgumentException("O paciente do agendamento é obrigatório.");
        }
        if (medicoId == null) {
            throw new IllegalArgumentException("O médico do agendamento é obrigatório.");
        }
        if (dataHora == null) {
            throw new IllegalArgumentException("A data e hora do agendamento são obrigatórias.");
        }
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo do agendamento é obrigatório.");
        }

        this.id = UUID.randomUUID();
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.tipo = tipo.trim();
        this.status = StatusAgendamento.CRIADO;
    }

    public void cancelar() {
        if (this.status == StatusAgendamento.CANCELADO) {
            throw new IllegalStateException("Este agendamento já foi cancelado.");
        }
        this.status = StatusAgendamento.CANCELADO;
    }

    public void concluir() {
        if (this.status == StatusAgendamento.CONCLUIDO) {
            throw new IllegalStateException("Este agendamento já foi concluído.");
        }
        this.status = StatusAgendamento.CONCLUIDO;
    }

    public UUID getId() { return id; }
    public UUID getPacienteId() { return pacienteId; }
    public UUID getMedicoId() { return medicoId; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getTipo() { return tipo; }
    public StatusAgendamento getStatus() { return status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}