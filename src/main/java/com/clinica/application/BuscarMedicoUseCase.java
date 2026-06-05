package com.clinica.application;

import com.clinica.domain.Medico;
import com.clinica.domain.CPF;
import com.clinica.domain.CRM;
import com.clinica.domain.Email;
import com.clinica.infrastructure.MedicoEntity;
import com.clinica.infrastructure.MedicoRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class BuscarMedicoUseCase {

    private final MedicoRepository repository;

    public BuscarMedicoUseCase(MedicoRepository repository) {
        this.repository = repository;
    }

    public Medico executar(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do médico não pode ser nulo.");
        }
        MedicoEntity entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado."));

        String[] partes = entity.getCrm().split("/");
        return new Medico(
            entity.getNome(),
            new CPF(entity.getCpf()),
            new CRM(partes[0], partes[1]),
            new Email(entity.getEmail())
        );
    }
}