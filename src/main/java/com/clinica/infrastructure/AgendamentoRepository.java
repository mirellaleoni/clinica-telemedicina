package com.clinica.infrastructure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, UUID> {

    List<AgendamentoEntity> findByPacienteId(UUID pacienteId);

    List<AgendamentoEntity> findByMedicoId(UUID medicoId);
    boolean existsByMedicoIdAndDataHora(UUID medicoId, LocalDateTime dataHora);
    List<AgendamentoEntity> findByStatus(String status);
    boolean existsByMedicoIdAndDataHoraAndIdNot(UUID medicoId, LocalDateTime dataHora, UUID id);
}
