package com.clinica.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Value Object Email")
class EmailTest {

    // ========== TESTES DE SUCESSO ==========

    @Test
    @DisplayName("Deve criar email válido")
    void deveCriarEmailValido() {
        // Arrange & Act
        Email email = new Email("usuario@example.com");

        // Assert
        assertEquals("usuario@example.com", email.getEndereco(), "Email deve ser armazenado corretamente");
    }

    @Test
    @DisplayName("Deve aceitar email com múltiplos pontos no domínio")
    void deveAceitarEmailComMultiplosPontos() {
        // Arrange & Act
        Email email = new Email("usuario@mail.example.com.br");

        // Assert
        assertEquals("usuario@mail.example.com.br", email.getEndereco());
    }

    @Test
    @DisplayName("Deve aceitar email com números e caracteres especiais válidos")
    void deveAceitarEmailComNumerosECaracteresValidos() {
        // Arrange & Act
        Email email = new Email("usuario123+tag@example.com");

        // Assert
        assertEquals("usuario123+tag@example.com", email.getEndereco());
    }

    @Test
    @DisplayName("Deve aceitar email com underscore e hífen")
    void deveAceitarEmailComUnderscoreEHifen() {
        // Arrange & Act
        Email email = new Email("usuario_name-123@example.com");

        // Assert
        assertEquals("usuario_name-123@example.com", email.getEndereco());
    }

    @Test
    @DisplayName("Deve considerar dois emails com mesmo endereço como iguais")
    void deveConsiderarDoisEmailsComoIguais() {
        // Arrange
        Email email1 = new Email("usuario@example.com");
        Email email2 = new Email("usuario@example.com");

        // Act & Assert
        assertEquals(email1, email2, "Emails com mesmo endereço devem ser iguais");
        assertEquals(email1.hashCode(), email2.hashCode(), "HashCode deve ser igual");
    }

    @Test
    @DisplayName("Deve gerar hashCode consistente")
    void deveGerarHashCodeConsistente() {
        // Arrange
        Email email1 = new Email("usuario@example.com");
        Email email2 = new Email("usuario@example.com");

        // Act
        int hash1 = email1.hashCode();
        int hash2 = email2.hashCode();

        // Assert
        assertEquals(hash1, hash2, "HashCode deve ser consistente");
    }

    // ========== TESTES DE FALHA ==========

    @Test
    @DisplayName("Deve lançar exceção quando email é nulo")
    void deveLancarExcecaoQuandoEmailEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email(null),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O email não pode ser nulo ou vazio.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email é vazio")
    void deveLancarExcecaoQuandoEmailEhVazio() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email(""),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O email não pode ser nulo ou vazio.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email é espaço em branco")
    void deveLancarExcecaoQuandoEmailEhEspacoEmBranco() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email("   "),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O email não pode ser nulo ou vazio.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando falta @ no email")
    void deveLancarExcecaoQuandoFaltaArrobaNoEmail() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email("usuarioexample.com"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("Formato de email inválido.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando falta domínio no email")
    void deveLancarExcecaoQuandoFaltaDominioNoEmail() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email("usuario@"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("Formato de email inválido.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando falta extensão no email")
    void deveLancarExcecaoQuandoFaltaExtensaoNoEmail() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email("usuario@example"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("Formato de email inválido.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email tem @ duplicado")
    void deveLancarExcecaoQuandoEmailTemArrobaDuplicada() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email("usuario@@example.com"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("Formato de email inválido.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email tem espaço")
    void deveLancarExcecaoQuandoEmailTemEspaco() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Email("usuario @example.com"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("Formato de email inválido.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve considerar dois emails diferentes como não iguais")
    void deveConsiderarDoisEmailsDiferentesComoNaoIguais() {
        // Arrange
        Email email1 = new Email("usuario1@example.com");
        Email email2 = new Email("usuario2@example.com");

        // Act & Assert
        assertNotEquals(email1, email2, "Emails diferentes devem ser diferentes");
        assertNotEquals(email1.hashCode(), email2.hashCode(), "HashCode deve ser diferente");
    }

    @Test
    @DisplayName("Email não deve ser igual a null")
    void emailNaoDeveSerIgualANull() {
        // Arrange
        Email email = new Email("usuario@example.com");

        // Act & Assert
        assertNotEquals(null, email, "Email não deve ser igual a null");
        assertFalse(email.equals(null), "equals(null) deve retornar false");
    }

    @Test
    @DisplayName("Email não deve ser igual a objeto de outro tipo")
    void emailNaoDeveSerIgualAOutroTipo() {
        // Arrange
        Email email = new Email("usuario@example.com");
        String texto = "usuario@example.com";

        // Act & Assert
        assertNotEquals(email, texto, "Email não deve ser igual a String");
        assertFalse(email.equals(texto), "equals(String) deve retornar false");
    }
}
