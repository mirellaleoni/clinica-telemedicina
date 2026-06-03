package com.clinica.domain.Agendamentos;

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
        // Arrange
        UUID pacienteId = UUID.randomUUID();
        UUID medicoId = UUID.randomUUID();
        LocalDateTime dataHora = LocalDateTime.now().plusDays(1);
        String tipo = "ONLINE";

        // Act
        Agendamento agendamento = new Agendamento(pacienteId, medicoId, dataHora, tipo);

        // Assert
        assertEquals(pacienteId, agendamento.getPacienteId(), "Paciente deve ser igual ao fornecido");
        assertEquals(medicoId, agendamento.getMedicoId(), "Médico deve ser igual ao fornecido");
        assertEquals(dataHora, agendamento.getDataHora(), "Data e hora devem ser iguais às fornecidas");
        assertEquals(tipo, agendamento.getTipo(), "Tipo deve ser igual ao fornecido");
        assertEquals(statusAgendamento.CRIADO, agendamento.getStatus(), "Status inicial deve ser CRIADO");
    }

    @Test
    @DisplayName("Deve remover espaços do tipo do agendamento")
    void deveRemoverEspacosDoTipoDoAgendamento() {
        // Arrange & Act
        Agendamento agendamento = new Agendamento(
            UUID.randomUUID(),
            UUID.randomUUID(),
            LocalDateTime.now().plusDays(1),
            " ONLINE "
        );

        // Assert
        assertEquals("ONLINE", agendamento.getTipo(), "Tipo deve ser armazenado sem espaços nas extremidades");
    }

    // ========== TESTES DE FALHA ==========

    @Test
    @DisplayName("Deve lançar exceção quando paciente é nulo")
    void deveLancarExcecaoQuandoPacienteEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Agendamento(null, UUID.randomUUID(), LocalDateTime.now().plusDays(1), "ONLINE"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O paciente do agendamento é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando médico é nulo")
    void deveLancarExcecaoQuandoMedicoEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Agendamento(UUID.randomUUID(), null, LocalDateTime.now().plusDays(1), "ONLINE"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O médico do agendamento é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando data e hora são nulas")
    void deveLancarExcecaoQuandoDataHoraEhNula() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Agendamento(UUID.randomUUID(), UUID.randomUUID(), null, "ONLINE"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("A data e hora do agendamento são obrigatórias.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando tipo é nulo")
    void deveLancarExcecaoQuandoTipoEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Agendamento(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now().plusDays(1), null),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O tipo do agendamento é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando tipo é vazio")
    void deveLancarExcecaoQuandoTipoEhVazio() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Agendamento(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now().plusDays(1), "   "),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O tipo do agendamento é obrigatório.", exception.getMessage());
    }
}
