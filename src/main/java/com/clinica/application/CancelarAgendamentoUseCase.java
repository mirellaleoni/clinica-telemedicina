package com.clinica.application;

import com.clinica.infrastructure.AgendamentoEntity;
import com.clinica.infrastructure.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CancelarAgendamentoUseCase {

    private final AgendamentoRepository repository;

    public CancelarAgendamentoUseCase(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public void executar(UUID id) {
        AgendamentoEntity entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado."));

        if (entity.getStatus().equals("CANCELADO")) {
            throw new IllegalStateException("Este agendamento já foi cancelado.");
        }

        entity.setStatus("CANCELADO");
        repository.save(entity);
    }
}