package com.clinica.application;

import com.clinica.infrastructure.AgendamentoEntity;
import com.clinica.infrastructure.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RemarcarAgendamentoUseCase {

    private final AgendamentoRepository repository;

    public RemarcarAgendamentoUseCase(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public void executar(UUID id, LocalDateTime novaDataHora) {
        if (novaDataHora == null) {
            throw new IllegalArgumentException("A nova data e hora são obrigatórias.");
        }

        AgendamentoEntity entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado."));

        if (entity.getStatus().equals("CANCELADO")) {
            throw new IllegalStateException("Não é possível remarcar um agendamento cancelado.");
        }

        if (repository.existsByMedicoIdAndDataHoraAndIdNot(entity.getMedicoId(), novaDataHora, id)) {
            throw new IllegalStateException("Já existe um agendamento para este médico neste horário.");
        }

        entity.setDataHora(novaDataHora);
        repository.save(entity);
    }
}