package com.clinica.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Value Object CRM")
class CRMTest {

    // ========== TESTES DE SUCESSO ==========

    @Test
    @DisplayName("Deve criar CRM com número e UF válidos")
    void deveCriarCRMComNumeroEUFValidos() {
        // Arrange & Act
        CRM crm = new CRM("123456", "SP");

        // Assert
        assertEquals("123456", crm.getNumero(), "Número do CRM deve ser armazenado corretamente");
        assertEquals("SP", crm.getUf(), "UF do CRM deve ser armazenada corretamente");
        assertEquals("123456/SP", crm.getValorCompleto(), "Valor completo deve juntar número e UF");
    }

    @Test
    @DisplayName("Deve remover espaços e normalizar UF para maiúsculo")
    void deveRemoverEspacosENormalizarUFParaMaiusculo() {
        // Arrange & Act
        CRM crm = new CRM(" 123456 ", "sp");

        // Assert
        assertEquals("123456", crm.getNumero(), "Número deve ser armazenado sem espaços");
        assertEquals("SP", crm.getUf(), "UF deve ser convertida para maiúsculo");
    }

    @Test
    @DisplayName("Deve considerar CRMs com mesmo número e UF como iguais")
    void deveConsiderarCRMsComMesmoNumeroEUFComoIguais() {
        // Arrange
        CRM crm1 = new CRM("123456", "SP");
        CRM crm2 = new CRM("123456", "sp");

        // Act & Assert
        assertEquals(crm1, crm2, "CRMs com mesmo número e UF devem ser iguais");
        assertEquals(crm1.hashCode(), crm2.hashCode(), "HashCode deve ser igual");
    }

    // ========== TESTES DE FALHA ==========

    @Test
    @DisplayName("Deve lançar exceção quando número é nulo")
    void deveLancarExcecaoQuandoNumeroEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CRM(null, "SP"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O número do CRM é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando número é vazio")
    void deveLancarExcecaoQuandoNumeroEhVazio() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CRM("   ", "SP"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O número do CRM é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando UF é nula")
    void deveLancarExcecaoQuandoUFEhNula() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CRM("123456", null),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("A UF do CRM é obrigatória e deve conter 2 caracteres.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando UF não tem dois caracteres")
    void deveLancarExcecaoQuandoUFNaoTemDoisCaracteres() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CRM("123456", "SPO"),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("A UF do CRM é obrigatória e deve conter 2 caracteres.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve considerar CRMs diferentes como não iguais")
    void deveConsiderarCRMsDiferentesComoNaoIguais() {
        // Arrange
        CRM crm1 = new CRM("123456", "SP");
        CRM crm2 = new CRM("654321", "RJ");

        // Act & Assert
        assertNotEquals(crm1, crm2, "CRMs diferentes não devem ser iguais");
        assertNotEquals(crm1.hashCode(), crm2.hashCode(), "HashCode deve ser diferente");
    }
}
