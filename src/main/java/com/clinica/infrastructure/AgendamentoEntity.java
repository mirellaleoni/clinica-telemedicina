package com.clinica.infrastructure;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "agenda")
public class AgendamentoEntity {

    @Id
    private UUID id;

    @Column(name = "paciente_id", nullable = false)
    private UUID pacienteId;

    @Column(name = "medico_id", nullable = false)
    private UUID medicoId;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String status;

    protected AgendamentoEntity() {}

    public AgendamentoEntity(UUID id, UUID pacienteId, UUID medicoId, LocalDateTime dataHora, String tipo, String status) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.status = status;
    }

    public UUID getId() { return id; }
    public UUID getPacienteId() { return pacienteId; }
    public UUID getMedicoId() { return medicoId; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getTipo() { return tipo; }
    public String getStatus() { return status; }

    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public void setStatus(String status) { this.status = status; }
}
