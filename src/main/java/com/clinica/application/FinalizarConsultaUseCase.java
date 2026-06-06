package com.clinica.application;

import com.clinica.infrastructure.ConsultaEntity;
import com.clinica.infrastructure.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FinalizarConsultaUseCase {

    private final ConsultaRepository repository;

    public FinalizarConsultaUseCase(ConsultaRepository repository) {
        this.repository = repository;
    }

    public ConsultaEntity executar(UUID id) {
        ConsultaEntity consulta = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada."));

        if (consulta.getFinalizadaEm() != null) {
            throw new IllegalStateException("Esta consulta já foi finalizada.");
        }

        consulta.setFinalizadaEm(LocalDateTime.now());
        return repository.save(consulta);
    }
}