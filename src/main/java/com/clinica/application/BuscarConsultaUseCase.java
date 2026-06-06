package com.clinica.application;

import com.clinica.infrastructure.ConsultaEntity;
import com.clinica.infrastructure.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarConsultaUseCase {

    private final ConsultaRepository repository;

    public BuscarConsultaUseCase(ConsultaRepository repository) {
        this.repository = repository;
    }

    public ConsultaEntity executar(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada."));
    }
}