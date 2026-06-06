package com.clinica.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProntuarioTest {

    @Test
    @DisplayName("Deve criar prontuário com dados válidos")
    void deveCriarProntuarioComSucesso() {
        UUID consultaId = UUID.randomUUID();
        String observacoes = "Paciente relata dores de cabeça constantes.";
        String prescricao = "Paracetamol 500mg de 6 em 6 horas se houver dor.";

        Prontuario prontuario = new Prontuario(consultaId, observacoes, prescricao);

        assertNotNull(prontuario.getId());
        assertEquals(consultaId, prontuario.getConsultaId());
        assertEquals(observacoes, prontuario.getObservacoes());
        assertEquals(prescricao, prontuario.getPrescricao());
        assertNotNull(prontuario.getCriadoEm());
    }

    @Test
    @DisplayName("Não deve criar prontuário sem ID da consulta")
    void naoDeveCriarSemConsultaId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(null, "Observações válidas", "Prescrição válida");
        });
    }

    @Test
    @DisplayName("Não deve criar prontuário com observações vazias")
    void naoDeveCriarComObservacoesVazias() {
        UUID consultaId = UUID.randomUUID();
        assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(consultaId, "   ", "Prescrição válida");
        });
    }

    @Test
    @DisplayName("Deve permitir adicionar evolução de histórico com sucesso")
    void deveAdicionarEvolucaoComSucesso() {
        UUID consultaId = UUID.randomUUID();
        Prontuario prontuario = new Prontuario(consultaId, "Primeira consulta: Sintomas leves.", "Repouso.");

        prontuario.adicionarEvolucao("Retorno: Paciente totalmente recuperado.");

        assertTrue(prontuario.getObservacoes().contains("Primeira consulta: Sintomas leves."));
        assertTrue(prontuario.getObservacoes().contains("Retorno: Paciente totalmente recuperado."));
    }
}