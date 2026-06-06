package com.clinica.application;

import com.clinica.infrastructure.ConsultaEntity;
import com.clinica.infrastructure.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class IniciarConsultaUseCase {

    private final ConsultaRepository repository;

    public IniciarConsultaUseCase(ConsultaRepository repository) {
        this.repository = repository;
    }

    public ConsultaEntity executar(UUID id) {
        ConsultaEntity consulta = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada."));

        if (consulta.getIniciadaEm() != null) {
            throw new IllegalStateException("Esta consulta já foi iniciada.");
        }

        consulta.setIniciadaEm(LocalDateTime.now());
        return repository.save(consulta);
    }
}