package com.clinica.application;

import com.clinica.infrastructure.ReceitaEntity;
import com.clinica.infrastructure.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarReceitaUseCase {

    private final ReceitaRepository repository;

    public BuscarReceitaUseCase(ReceitaRepository repository) {
        this.repository = repository;
    }

    public ReceitaEntity executar(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Receita não encontrada."));
    }
}