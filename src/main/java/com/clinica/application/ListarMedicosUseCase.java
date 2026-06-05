package com.clinica.application;

import com.clinica.domain.Medico;
import com.clinica.domain.CPF;
import com.clinica.domain.CRM;
import com.clinica.domain.Email;
import com.clinica.infrastructure.MedicoEntity;
import com.clinica.infrastructure.MedicoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListarMedicosUseCase {

    private final MedicoRepository repository;

    public ListarMedicosUseCase(MedicoRepository repository) {
        this.repository = repository;
    }

    public List<Medico> executar() {
        return repository.findAll()
            .stream()
            .map(this::toDomain)
            .toList();
    }

    private Medico toDomain(MedicoEntity entity) {
        String[] partes = entity.getCrm().split("/");
        return new Medico(
            entity.getNome(),
            new CPF(entity.getCpf()),
            new CRM(partes[0], partes[1]),
            new Email(entity.getEmail())
        );
    }
}