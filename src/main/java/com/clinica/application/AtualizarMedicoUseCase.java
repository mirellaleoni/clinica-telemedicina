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
        
        // 1. Busca o médico existente no banco
        MedicoEntity entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado."));

        // 2. Atualiza o nome se foi enviado
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            entity.setNome(novoNome);
        }

        // 3. Valida e atualiza o e-mail se foi enviado
        if (novoEmail != null && !novoEmail.trim().isEmpty()) {
            new Email(novoEmail); // Valida o formato pelo domain
            entity.setEmail(novoEmail);
        }

        // 4. Salva a própria entidade modificada (O Spring faz o UPDATE automaticamente)
        repository.save(entity);
    }
}