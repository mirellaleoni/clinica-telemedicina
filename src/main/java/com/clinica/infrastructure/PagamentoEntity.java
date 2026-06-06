package com.clinica.infrastructure;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamentos")
public class PagamentoEntity {

    @Id
    private UUID id;

    @Column(name = "agendamento_id", nullable = false)
    private UUID agendamentoId;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(name = "forma_pagamento", nullable = false)
    private String formaPagamento;

    @Column(nullable = false)
    private String status;

    protected PagamentoEntity() {}

    public PagamentoEntity(UUID id, UUID agendamentoId, BigDecimal valor, String formaPagamento, String status) {
        this.id = id;
        this.agendamentoId = agendamentoId;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.status = status;
    }

    public UUID getId() { return id; }
    public UUID getAgendamentoId() { return agendamentoId; }
    public BigDecimal getValor() { return valor; }
    public String getFormaPagamento() { return formaPagamento; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
