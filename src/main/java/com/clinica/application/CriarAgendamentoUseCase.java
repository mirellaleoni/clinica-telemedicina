package com.clinica.application;

import com.clinica.domain.Agendamento;
import com.clinica.infrastructure.AgendamentoEntity;
import com.clinica.infrastructure.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CriarAgendamentoUseCase {

    private final AgendamentoRepository repository;

    public CriarAgendamentoUseCase(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public Agendamento executar(UUID pacienteId, UUID medicoId, LocalDateTime dataHora, String tipo) {
        if (repository.existsByMedicoIdAndDataHora(medicoId, dataHora)) {
            throw new IllegalStateException("Já existe um agendamento para este médico neste horário.");
        }

        Agendamento agendamento = new Agendamento(pacienteId, medicoId, dataHora, tipo);

        AgendamentoEntity entity = new AgendamentoEntity(
            agendamento.getId(),
            agendamento.getPacienteId(),
            agendamento.getMedicoId(),
            agendamento.getDataHora(),
            agendamento.getTipo(),
            agendamento.getStatus().name()
        );

        repository.save(entity);
        return agendamento;
    }
}