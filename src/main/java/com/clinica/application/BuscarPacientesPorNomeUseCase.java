package com.clinica.application;

import com.clinica.infrastructure.PacienteEntity;
import com.clinica.infrastructure.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarPacientesPorNomeUseCase {

    private final PacienteRepository repository;

    public BuscarPacientesPorNomeUseCase(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<PacienteEntity> executar(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}