package com.clinica.application;

import com.clinica.domain.Email;
import com.clinica.infrastructure.MedicoEntity;
import com.clinica.infrastructure.MedicoRepository;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AtualizarMedicoUseCase {

    private final MedicoRepository repository;

    public AtualizarMedicoUseCase(MedicoRepository repository) {
        this.repository = repository;
    }

    public void executar(UUID id, String novoNome, String novoEmail) {
        if (id == null) {
            throw new IllegalArgumentException("ID do médico não pode ser nulo.");
        }
        MedicoEntity entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado."));

        if (novoNome != null && !novoNome.trim().isEmpty()) {
            entity.setNome(novoNome);
        }

        if (novoEmail != null && !novoEmail.trim().isEmpty()) {
            new Email(novoEmail); // valida o formato pelo domain
            entity.setEmail(novoEmail);
        }

                MedicoEntity entityAtualizada = new MedicoEntity(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getCrm(),
                novoEmail != null ? novoEmail : entity.getEmail()
        );

        repository.save(entityAtualizada);
    }
}

