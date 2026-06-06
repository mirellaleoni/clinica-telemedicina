package com.clinica.infrastructure;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {

    List<PagamentoEntity> findByAgendamentoId(UUID agendamentoId);
}