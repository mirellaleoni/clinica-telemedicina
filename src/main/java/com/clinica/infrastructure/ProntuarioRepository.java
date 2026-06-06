package com.clinica.infrastructure;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<ProntuarioEntity, UUID> {

    List<ProntuarioEntity> findByConsultaId(UUID consultaId);
}