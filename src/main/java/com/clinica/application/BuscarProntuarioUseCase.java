package com.clinica.application;

import com.clinica.infrastructure.ProntuarioEntity;
import com.clinica.infrastructure.ProntuarioRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarProntuarioUseCase {

    private final ProntuarioRepository repository;

    public BuscarProntuarioUseCase(ProntuarioRepository repository) {
        this.repository = repository;
    }

    public ProntuarioEntity executar(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Prontuário não encontrado."));
    }
}