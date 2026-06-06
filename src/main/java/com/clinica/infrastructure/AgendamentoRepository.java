package com.clinica.infrastructure;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, UUID> {

    List<AgendamentoEntity> findByPacienteId(UUID pacienteId);

    List<AgendamentoEntity> findByMedicoId(UUID medicoId);
}
