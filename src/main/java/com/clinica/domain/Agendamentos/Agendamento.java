package com.clinica.domain.Agendamentos;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)


public class Agendamento {
    

    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private LocalDateTime dataHora;
    private String motivo;
    private statusAgendamento status;

    public Agendamento(Long pacienteId, Long medicoId, LocalDateTime dataHora, String motivo) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.motivo = motivo;
    }

}