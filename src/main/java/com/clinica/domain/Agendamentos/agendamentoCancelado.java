package com.clinica.domain.Agendamentos;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agendamento_cancelado")
@Getter
@NoArgsConstructor

public class agendamentoCancelado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;

    private String motivo;
    private Long usuarioCancelamentoId;
    private LocalDateTime dataCancelamento;

    public void AgendamentoCancelado(Agendamento agendamento, String motivo, Long usuarioCancelamentoId) {
        this.agendamento = agendamento;
        this.motivo = motivo;
        this.usuarioCancelamentoId = usuarioCancelamentoId;
        this.dataCancelamento = LocalDateTime.now();
    }
    
}
