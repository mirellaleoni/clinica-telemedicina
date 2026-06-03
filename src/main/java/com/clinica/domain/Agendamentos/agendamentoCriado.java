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
@Table(name = "agendamento_criado")
@Getter
@NoArgsConstructor

public class agendamentoCriado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;

    private LocalDateTime dataCriacao;

    public agendamentoCriado(Agendamento agendamento) {
        this.agendamento = agendamento;
        this.dataCriacao = LocalDateTime.now();
    }
    
}
