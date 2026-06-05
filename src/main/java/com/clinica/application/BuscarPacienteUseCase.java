package com.clinica.application;

import com.clinica.infrastructure.PacienteEntity;
import com.clinica.infrastructure.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarPacienteUseCase {

    private final PacienteRepository repository;

    public BuscarPacienteUseCase(PacienteRepository repository) {
        this.repository = repository;
    }

    public Optional<PacienteEntity> executar(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do paciente não pode ser nulo para busca.");
        }
        return repository.findById(id);
    }
}