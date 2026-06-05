package com.clinica.application;

import com.clinica.infrastructure.PacienteEntity;
import com.clinica.infrastructure.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPacientesUseCase {

    private final PacienteRepository repository;

    public ListarPacientesUseCase(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<PacienteEntity> executar() {
        
        return repository.findAll();
    }
}