package com.clinica.application;

import com.clinica.infrastructure.AgendamentoEntity;
import com.clinica.infrastructure.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarAgendamentosUseCase {

    private final AgendamentoRepository repository;

    public ListarAgendamentosUseCase(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public List<AgendamentoEntity> executar() {
        return repository.findAll();
    }

    public List<AgendamentoEntity> porPaciente(UUID pacienteId) {
        return repository.findByPacienteId(pacienteId);
    }

    public List<AgendamentoEntity> porMedico(UUID medicoId) {
        return repository.findByMedicoId(medicoId);
    }
}