package com.clinica.application;

import com.clinica.domain.Prontuario;
import com.clinica.infrastructure.ProntuarioEntity;
import com.clinica.infrastructure.ProntuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CriarProntuarioUseCase {

    private final ProntuarioRepository repository;

    public CriarProntuarioUseCase(ProntuarioRepository repository) {
        this.repository = repository;
    }

    public Prontuario executar(UUID consultaId, String observacoes, String prescricao) {
        Prontuario prontuario = new Prontuario(consultaId, observacoes, prescricao);

        ProntuarioEntity entity = new ProntuarioEntity(
            prontuario.getId(),
            prontuario.getConsultaId(),
            prontuario.getObservacoes(),
            prontuario.getPrescricao(),
            LocalDateTime.now()
        );

        repository.save(entity);
        return prontuario;
    }
}