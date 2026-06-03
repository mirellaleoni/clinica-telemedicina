package com.clinica.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Value Object HorarioDisponivel")
class HorarioDisponivelTest {

    // ========== TESTES DE SUCESSO ==========

    @Test
    @DisplayName("Deve criar horário disponível com dados válidos")
    void deveCriarHorarioDisponivelComDadosValidos() {
        // Arrange
        DayOfWeek diaSemana = DayOfWeek.MONDAY;
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFim = LocalTime.of(12, 0);

        // Act
        HorarioDisponivel horario = new HorarioDisponivel(diaSemana, horaInicio, horaFim);

        // Assert
        assertEquals(diaSemana, horario.getDiaSemana(), "Dia da semana deve ser igual ao fornecido");
        assertEquals(horaInicio, horario.getHoraInicio(), "Hora de início deve ser igual à fornecida");
        assertEquals(horaFim, horario.getHoraFim(), "Hora de fim deve ser igual à fornecida");
    }

    @Test
    @DisplayName("Deve identificar horário dentro do intervalo disponível")
    void deveIdentificarHorarioDentroDoIntervaloDisponivel() {
        // Arrange
        HorarioDisponivel horario = new HorarioDisponivel(
            DayOfWeek.TUESDAY,
            LocalTime.of(9, 0),
            LocalTime.of(11, 0)
        );

        // Act & Assert
        assertTrue(horario.contem(LocalTime.of(9, 0)), "Horário inicial deve estar incluído");
        assertTrue(horario.contem(LocalTime.of(10, 30)), "Horário dentro do intervalo deve estar incluído");
        assertFalse(horario.contem(LocalTime.of(11, 0)), "Horário final não deve estar incluído");
    }

    @Test
    @DisplayName("Deve considerar horários iguais como iguais")
    void deveConsiderarHorariosIguaisComoIguais() {
        // Arrange
        HorarioDisponivel horario1 = new HorarioDisponivel(
            DayOfWeek.WEDNESDAY,
            LocalTime.of(14, 0),
            LocalTime.of(18, 0)
        );
        HorarioDisponivel horario2 = new HorarioDisponivel(
            DayOfWeek.WEDNESDAY,
            LocalTime.of(14, 0),
            LocalTime.of(18, 0)
        );

        // Act & Assert
        assertEquals(horario1, horario2, "Horários com os mesmos dados devem ser iguais");
        assertEquals(horario1.hashCode(), horario2.hashCode(), "HashCode deve ser igual");
    }

    // ========== TESTES DE FALHA ==========

    @Test
    @DisplayName("Deve lançar exceção quando dia da semana é nulo")
    void deveLancarExcecaoQuandoDiaSemanaEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new HorarioDisponivel(null, LocalTime.of(8, 0), LocalTime.of(12, 0)),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O dia da semana é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando hora de início é nula")
    void deveLancarExcecaoQuandoHoraInicioEhNula() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new HorarioDisponivel(DayOfWeek.MONDAY, null, LocalTime.of(12, 0)),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("A hora de início é obrigatória.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando hora de fim é nula")
    void deveLancarExcecaoQuandoHoraFimEhNula() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new HorarioDisponivel(DayOfWeek.MONDAY, LocalTime.of(8, 0), null),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("A hora de fim é obrigatória.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando hora de fim é igual à hora de início")
    void deveLancarExcecaoQuandoHoraFimEhIgualAHoraInicio() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new HorarioDisponivel(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(8, 0)),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("A hora de fim deve ser posterior à hora de início.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando hora de fim é anterior à hora de início")
    void deveLancarExcecaoQuandoHoraFimEhAnteriorAHoraInicio() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new HorarioDisponivel(DayOfWeek.MONDAY, LocalTime.of(12, 0), LocalTime.of(8, 0)),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("A hora de fim deve ser posterior à hora de início.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao verificar horário nulo")
    void deveLancarExcecaoAoVerificarHorarioNulo() {
        // Arrange
        HorarioDisponivel horario = new HorarioDisponivel(
            DayOfWeek.FRIDAY,
            LocalTime.of(8, 0),
            LocalTime.of(12, 0)
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> horario.contem(null),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O horário é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve considerar horários diferentes como não iguais")
    void deveConsiderarHorariosDiferentesComoNaoIguais() {
        // Arrange
        HorarioDisponivel horario1 = new HorarioDisponivel(
            DayOfWeek.MONDAY,
            LocalTime.of(8, 0),
            LocalTime.of(12, 0)
        );
        HorarioDisponivel horario2 = new HorarioDisponivel(
            DayOfWeek.TUESDAY,
            LocalTime.of(8, 0),
            LocalTime.of(12, 0)
        );

        // Act & Assert
        assertNotEquals(horario1, horario2, "Horários diferentes não devem ser iguais");
        assertNotEquals(horario1.hashCode(), horario2.hashCode(), "HashCode deve ser diferente");
    }
}
