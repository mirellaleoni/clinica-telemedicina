package com.clinica.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, UUID> {
    
    List<PacienteEntity> findByNomeContainingIgnoreCase(String q);
    
}