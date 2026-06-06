package com.clinica.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agenda")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID pacienteId;
    private UUID medicoId;
    private LocalDateTime dataHora;
    private String tipo;

    @Enumerated(EnumType.STRING)
    private statusAgendamento status;

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

        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.tipo = tipo.trim();
        this.status = statusAgendamento.CRIADO;
    }

}
