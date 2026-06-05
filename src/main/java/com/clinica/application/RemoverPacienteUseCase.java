package com.clinica.application;

import com.clinica.infrastructure.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoverPacienteUseCase {

    private final PacienteRepository repository;

    public RemoverPacienteUseCase(PacienteRepository repository) {
        this.repository = repository;
    }

    public void executar(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do paciente não pode ser nulo para remoção.");
        }
        if (!repository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado para remoção.");
        }
        
        repository.deleteById(id);
    }
}