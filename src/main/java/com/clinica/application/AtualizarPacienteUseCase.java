package com.clinica.application;

import com.clinica.domain.CPF;
import com.clinica.domain.Paciente;
import com.clinica.infrastructure.PacienteEntity;
import com.clinica.infrastructure.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarPacienteUseCase {

    private final PacienteRepository repository;

    public AtualizarPacienteUseCase(PacienteRepository repository) {
        this.repository = repository;
    }

    public PacienteEntity executar(UUID id, String novoNome, String novoTelefone) {
        PacienteEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado para atualização."));

        CPF cpf = new CPF(entity.getCpf());
        Paciente paciente = new Paciente(entity.getNome(), cpf);

        if (novoNome != null && !novoNome.isBlank()) {
            paciente.alterarNome(novoNome);
        }

        PacienteEntity entityAtualizada = new PacienteEntity(
                entity.getId(),
                paciente.getNome(),
                entity.getDataNascimento(),
                novoTelefone != null ? novoTelefone : entity.getTelefone(),
                entity.getCpf(),
                entity.getCriadoEm()
        );

        return repository.save(entityAtualizada);
    }
}