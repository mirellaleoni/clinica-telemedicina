package com.clinica.application;

import com.clinica.domain.Receita;
import com.clinica.infrastructure.ConsultaEntity;
import com.clinica.infrastructure.ConsultaRepository;
import com.clinica.infrastructure.ReceitaEntity;
import com.clinica.infrastructure.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmitirReceitaUseCase {

    private final ReceitaRepository receitaRepository;
    private final ConsultaRepository consultaRepository;

    public EmitirReceitaUseCase(ReceitaRepository receitaRepository, ConsultaRepository consultaRepository) {
        this.receitaRepository = receitaRepository;
        this.consultaRepository = consultaRepository;
    }

    public ReceitaEntity executar(UUID consultaId, String medicamentos, String instrucoes) {
        
        ConsultaEntity consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada no sistema."));

        if (consulta.getFinalizadaEm() == null) {
            throw new IllegalStateException("Não é possível emitir receita para uma consulta que não foi finalizada.");
        }

        Receita receita = new Receita(consultaId, medicamentos, instrucoes);

        ReceitaEntity entity = new ReceitaEntity(
                receita.getId(),
                receita.getConsultaId(),
                receita.getMedicamentos(),
                receita.getInstrucoes(),
                receita.getEmitidaEm()
        );

        return receitaRepository.save(entity);
    }
}