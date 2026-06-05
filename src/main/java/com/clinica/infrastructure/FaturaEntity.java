package com.clinica.infrastructure;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contas")
public class FaturaEntity {

    @Id
    private UUID id;

    @Column(name = "paciente_id", nullable = false)
    private UUID pacienteId;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private LocalDate vencimento;

    @Column(nullable = false)
    private String status;

    protected FaturaEntity() {}

    public FaturaEntity(UUID id, UUID pacienteId, BigDecimal total, LocalDate vencimento, String status) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.total = total;
        this.vencimento = vencimento;
        this.status = status;
    }

    public UUID getId() { return id; }
    public UUID getPacienteId() { return pacienteId; }
    public BigDecimal getTotal() { return total; }
    public LocalDate getVencimento() { return vencimento; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}
