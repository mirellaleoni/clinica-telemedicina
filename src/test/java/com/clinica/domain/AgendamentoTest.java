package com.clinica.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Aggregate Root Agendamento")
class AgendamentoTest {

    // ========== TESTES DE SUCESSO ==========

    @Test
    @DisplayName("Deve criar agendamento com dados válidos")
    void deveCriarAgendamentoComDadosValidos() {
        UUID pacienteId = UUID.randomUUID();
        UUID medicoId = UUID.randomUUID();
        LocalDateTime dataHora = LocalDateTime.now().plusDays(1);

        Agendamento agendamento = new Agendamento(pacienteId, medicoId, dataHora, "ONLINE");

        assertEquals(pacienteId, agendamento.getPacienteId());
        assertEquals(medicoId, agendamento.getMedicoId());
        assertEquals(dataHora, agendamento.getDataHora());
        assertEquals("ONLINE", agendamento.getTipo());
        assertEquals(StatusAgendamento.CRIADO, agendamento.getStatus());
    }

    @Test
    @DisplayName("Deve remover espaços do tipo do agendamento")
    void deveRemoverEspacosDoTipoDoAgendamento() {
        Agendamento agendamento = new Agendamento(
            UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now().plusDays(1), " ONLINE "
        );
        assertEquals("ONLINE", agendamento.getTipo());
    }

    @Test
    @DisplayName("Deve cancelar agendamento com sucesso")
    void deveCancelarAgendamento() {
        Agendamento agendamento = new Agendamento(
            UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now().plusDays(1), "ONLINE"
        );

        agendamento.cancelar();

        assertEquals(StatusAgendamento.CANCELADO, agendamento.getStatus());
    }

    @Test
    @DisplayName("Deve concluir agendamento com sucesso")
    void deveConcluirAgendamento() {
        Agendamento agendamento = new Agendamento(
            UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now().plusDays(1), "ONLINE"
        );

        agendamento.concluir();

        assertEquals(StatusAgendamento.CONCLUIDO, agendamento.getStatus());
    }

    // ========== TESTES DE FALHA ==========

    @Test
    @DisplayName("Deve lançar exceção quando paciente é nulo")
    void deveLancarExcecaoQuandoPacienteEhNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Agendamento(null, UUID.randomUUID(), LocalDateTime.now().plusDays(1), "ONLINE")
        );
        assertEquals("O paciente do agendamento é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando médico é nulo")
    void deveLancarExcecaoQuandoMedicoEhNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Agendamento(UUID.randomUUID(), null, LocalDateTime.now().plusDays(1), "ONLINE")
        );
        assertEquals("O médico do agendamento é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando data e hora são nulas")
    void deveLancarExcecaoQuandoDataHoraEhNula() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Agendamento(UUID.randomUUID(), UUID.randomUUID(), null, "ONLINE")
        );
        assertEquals("A data e hora do agendamento são obrigatórias.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando tipo é nulo")
    void deveLancarExcecaoQuandoTipoEhNulo() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Agendamento(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now().plusDays(1), null)
        );
        assertEquals("O tipo do agendamento é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando tipo é vazio")
    void deveLancarExcecaoQuandoTipoEhVazio() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Agendamento(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now().plusDays(1), "   ")
        );
        assertEquals("O tipo do agendamento é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cancelar agendamento já cancelado")
    void deveLancarExcecaoAoCancelarAgendamentoJaCancelado() {
        Agendamento agendamento = new Agendamento(
            UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now().plusDays(1), "ONLINE"
        );
        agendamento.cancelar();

        assertThrows(IllegalStateException.class, agendamento::cancelar);
    }

    @Test
    @DisplayName("Deve lançar exceção ao concluir agendamento já concluído")
    void deveLancarExcecaoAoConcluirAgendamentoJaConcluido() {
        Agendamento agendamento = new Agendamento(
            UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now().plusDays(1), "ONLINE"
        );
        agendamento.concluir();

        assertThrows(IllegalStateException.class, agendamento::concluir);
    }
}