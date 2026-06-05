package com.clinica.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Entidade Paciente")
class PacienteTest {

    // ========== TESTES DE SUCESSO ==========

    @Test
    @DisplayName("Deve criar paciente com dados válidos")
    void deveCreatePacienteComDadosValidos() {
        // Arrange
        CPF cpf = new CPF("12345678900");
        String nome = "João Silva";

        // Act
        Paciente paciente = new Paciente(nome, cpf);

        // Assert
        assertNotNull(paciente.getId(), "ID não deve ser nulo");
        assertEquals(nome, paciente.getNome(), "Nome deve ser igual ao fornecido");
        assertEquals(cpf, paciente.getCpf(), "CPF deve ser igual ao fornecido");
    }

    @Test
    @DisplayName("Deve gerar UUID único para cada paciente")
    void deveGerarUUIDUnicoParaCadaPaciente() {
        // Arrange
        CPF cpf1 = new CPF("12345678900");
        CPF cpf2 = new CPF("98765432100");

        // Act
        Paciente paciente1 = new Paciente("João", cpf1);
        Paciente paciente2 = new Paciente("Maria", cpf2);

        // Assert
        assertNotEquals(paciente1.getId(), paciente2.getId(), "IDs devem ser diferentes");
    }

    @Test
    @DisplayName("Deve alterar nome do paciente com valor válido")
    void deveAlterarNomeComValorValido() {
        // Arrange
        Paciente paciente = new Paciente("João", new CPF("12345678900"));
        String novoNome = "João Silva";

        // Act
        paciente.alterarNome(novoNome);

        // Assert
        assertEquals(novoNome, paciente.getNome(), "Nome deve ser alterado");
    }

    @Test
    @DisplayName("Deve comparar pacientes pela identidade (UUID)")
    void deveCompararPacientePelaIdentidade() {
        // Arrange
        CPF cpf = new CPF("12345678900");
        Paciente paciente1 = new Paciente("João", cpf);
        Paciente paciente2 = paciente1;

        // Act & Assert
        assertEquals(paciente1, paciente2, "Pacientes com mesmo UUID devem ser iguais");
        assertEquals(paciente1.hashCode(), paciente2.hashCode(), "HashCode deve ser igual");
    }

    // ========== TESTES DE FALHA ==========

    @Test
    @DisplayName("Deve lançar exceção quando nome é nulo")
    void deveLancarExcecaoQuandoNomeEhNulo() {
        // Arrange
        CPF cpf = new CPF("12345678900");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Paciente(null, cpf),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O nome do paciente é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome é vazio")
    void deveLancarExcecaoQuandoNomeEhVazio() {
        // Arrange
        CPF cpf = new CPF("12345678900");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Paciente("   ", cpf),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O nome do paciente é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF é nulo")
    void deveLancarExcecaoQuandoCpfEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Paciente("João", null),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O CPF do paciente é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao alterar nome para nulo")
    void deveLancarExcecaoAoAlterarNomeParaNulo() {
        // Arrange
        Paciente paciente = new Paciente("João", new CPF("12345678900"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> paciente.alterarNome(null),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O nome não pode ser vazio.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao alterar nome para vazio")
    void deveLancarExcecaoAoAlterarNomeParaVazio() {
        // Arrange
        Paciente paciente = new Paciente("João", new CPF("12345678900"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> paciente.alterarNome("   "),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O nome não pode ser vazio.", exception.getMessage());
    }

    @Test
    @DisplayName("Não deve considerar pacientes diferentes como iguais")
    void naoDeveConsiderarPacientesDiferentesComoIguais() {
        // Arrange
        Paciente paciente1 = new Paciente("João", new CPF("12345678900"));
        Paciente paciente2 = new Paciente("João", new CPF("12345678900"));

        // Act & Assert
        assertNotEquals(paciente1, paciente2, "Pacientes com IDs diferentes não devem ser iguais");
        assertNotEquals(paciente1.hashCode(), paciente2.hashCode(), "HashCode deve ser diferente");
    }
}