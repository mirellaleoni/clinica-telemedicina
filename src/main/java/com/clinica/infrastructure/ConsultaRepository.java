package com.clinica.infrastructure;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, UUID> {
    List<ConsultaEntity> findByAgendamentoId(UUID agendamentoId);
}