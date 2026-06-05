package com.clinica.infrastructure;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaturaRepository extends JpaRepository<FaturaEntity, UUID> {

    List<FaturaEntity> findByPacienteId(UUID pacienteId);
    List<FaturaEntity> findByPacienteIdAndStatus(UUID pacienteId, String status);

}