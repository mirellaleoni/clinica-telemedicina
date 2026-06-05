package com.clinica.application;

import com.clinica.infrastructure.MedicoRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class DesativarMedicoUseCase {

    private final MedicoRepository repository;

    public DesativarMedicoUseCase(MedicoRepository repository) {
        this.repository = repository;
    }

    public void executar(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do médico não pode ser nulo para desativação.");
        }
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Médico não encontrado.");
        }
        repository.deleteById(id);
    }
}