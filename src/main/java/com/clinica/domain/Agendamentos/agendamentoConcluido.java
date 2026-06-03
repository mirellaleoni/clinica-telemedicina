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
@Table(name = "agendamento_concluido")
@Getter
@NoArgsConstructor

public class agendamentoConcluido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;

    private Long usuarioConclusaoId;
    private LocalDateTime dataConclusao;

    public agendamentoConcluido(Agendamento agendamento, Long usuarioConclusaoId) {
        this.agendamento = agendamento;
        this.usuarioConclusaoId = usuarioConclusaoId;
        this.dataConclusao = LocalDateTime.now();
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

}
