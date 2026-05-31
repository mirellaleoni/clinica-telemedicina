package com.clinica.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Value Object CPF")
class CPFTest {

    // ========== TESTES DE SUCESSO ==========

    @Test
    @DisplayName("Deve criar CPF válido com 11 dígitos")
    void deveCriarCPFValidoComOnzeDigitos() {
        // Arrange & Act
        CPF cpf = new CPF("12345678900");

        // Assert
        assertEquals("12345678900", cpf.getNumero(), "CPF deve ser armazenado corretamente");
    }

    @Test
    @DisplayName("Deve aceitar CPF com caracteres especiais e remover")
    void deveAceitarCPFComCaracteresEspeciais() {
        // Arrange & Act
        CPF cpf = new CPF("123.456.789-00");

        // Assert
        assertEquals("12345678900", cpf.getNumero(), "CPF deve remover caracteres especiais");
    }

    @Test
    @DisplayName("Deve considerar dois CPFs com mesmo número como iguais")
    void deveConsiderarDoisCPFsComoIguais() {
        // Arrange
        CPF cpf1 = new CPF("12345678900");
        CPF cpf2 = new CPF("12345678900");

        // Act & Assert
        assertEquals(cpf1, cpf2, "CPFs com mesmo número devem ser iguais");
        assertEquals(cpf1.hashCode(), cpf2.hashCode(), "HashCode deve ser igual");
    }

    @Test
    @DisplayName("Deve gerar hashCode consistente")
    void deveGerarHashCodeConsistente() {
        // Arrange
        CPF cpf1 = new CPF("12345678900");
        CPF cpf2 = new CPF("12345678900");

        // Act
        int hash1 = cpf1.hashCode();
        int hash2 = cpf2.hashCode();

        // Assert
        assertEquals(hash1, hash2, "HashCode deve ser consistente");
    }

    // ========== TESTES DE FALHA ==========

    @Test
    @DisplayName("Deve lançar exceção quando CPF é nulo")
    void deveLancarExcecaoQuandoCPFEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CPF(null),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O CPF não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF é vazio")
    void deveLancarExcecaoQuandoCPFEhVazio() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CPF(""),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O CPF não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF tem menos de 11 dígitos")
    void deveLancarExcecaoQuandoCPFTemMenosDeOnzeDigitos() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CPF("1234567890"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O CPF deve conter exatamente 11 digitos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF tem mais de 11 dígitos")
    void deveLancarExcecaoQuandoCPFTemMaisDeOnzeDigitos() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CPF("123456789001"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O CPF deve conter exatamente 11 digitos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF contém apenas letras")
    void deveLancarExcecaoQuandoCPFContemApenasLetras() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CPF("abcdefghijk"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O CPF deve conter exatamente 11 digitos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve considerar dois CPFs com números diferentes como diferentes")
    void deveConsiderarDoisCPFsDiferentesComoNaoIguais() {
        // Arrange
        CPF cpf1 = new CPF("12345678900");
        CPF cpf2 = new CPF("98765432100");

        // Act & Assert
        assertNotEquals(cpf1, cpf2, "CPFs com números diferentes devem ser diferentes");
        assertNotEquals(cpf1.hashCode(), cpf2.hashCode(), "HashCode deve ser diferente");
    }

    @Test
    @DisplayName("Deve remover formatação: 123.456.789-00")
    void deveRemoverFormatacaoDoPonto() {
        // Arrange & Act
        CPF cpf = new CPF("123.456.789-00");

        // Assert
        assertEquals("12345678900", cpf.getNumero(), "Deve remover pontos e hífen");
    }

    @Test
    @DisplayName("CPF não deve ser igual a null")
    void cpfNaoDeveSerIgualANull() {
        // Arrange
        CPF cpf = new CPF("12345678900");

        // Act & Assert
        assertNotEquals(null, cpf, "CPF não deve ser igual a null");
        assertFalse(cpf.equals(null), "equals(null) deve retornar false");
    }

    @Test
    @DisplayName("CPF não deve ser igual a objeto de outro tipo")
    void cpfNaoDeveSerIgualAOutroTipo() {
        // Arrange
        CPF cpf = new CPF("12345678900");
        String texto = "12345678900";

        // Act & Assert
        assertNotEquals(cpf, texto, "CPF não deve ser igual a String");
        assertFalse(cpf.equals(texto), "equals(String) deve retornar false");
    }
}
