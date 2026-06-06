package com.clinica.application;

import com.clinica.infrastructure.AgendamentoEntity;
import com.clinica.infrastructure.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarAgendamentoUseCase {

    private final AgendamentoRepository repository;

    public BuscarAgendamentoUseCase(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public AgendamentoEntity executar(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado."));
    }
}