package com.clinica.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Entidade Medico")
class MedicoTest {

    // ========== TESTES DE SUCESSO ==========

    @Test
    @DisplayName("Deve criar médico com dados válidos")
    void deveCriarMedicoComDadosValidos() {
        // Arrange
        CPF cpf = new CPF("12345678900");
        CRM crm = new CRM("123456", "SP");
        Email email = new Email("medico@example.com");
        String nome = "Dra. Ana Souza";

        // Act
        Medico medico = new Medico(nome, cpf, crm, email);

        // Assert
        assertNotNull(medico.getId(), "ID não deve ser nulo");
        assertEquals(nome, medico.getNome(), "Nome deve ser igual ao fornecido");
        assertEquals(cpf, medico.getCpf(), "CPF deve ser igual ao fornecido");
        assertEquals(crm, medico.getCrm(), "CRM deve ser igual ao fornecido");
        assertEquals(email, medico.getEmail(), "Email deve ser igual ao fornecido");
    }

    @Test
    @DisplayName("Deve gerar UUID único para cada médico")
    void deveGerarUUIDUnicoParaCadaMedico() {
        // Arrange & Act
        Medico medico1 = criarMedico("Dr. João", "12345678900", "123456", "joao@example.com");
        Medico medico2 = criarMedico("Dra. Maria", "98765432100", "654321", "maria@example.com");

        // Assert
        assertNotEquals(medico1.getId(), medico2.getId(), "IDs devem ser diferentes");
    }

    @Test
    @DisplayName("Deve alterar nome do médico com valor válido")
    void deveAlterarNomeDoMedicoComValorValido() {
        // Arrange
        Medico medico = criarMedico("Dr. João", "12345678900", "123456", "joao@example.com");
        String novoNome = "Dr. João Silva";

        // Act
        medico.alterarNome(novoNome);

        // Assert
        assertEquals(novoNome, medico.getNome(), "Nome deve ser alterado");
    }

    @Test
    @DisplayName("Deve alterar email do médico com valor válido")
    void deveAlterarEmailDoMedicoComValorValido() {
        // Arrange
        Medico medico = criarMedico("Dr. João", "12345678900", "123456", "joao@example.com");
        Email novoEmail = new Email("joao.silva@example.com");

        // Act
        medico.alterarEmail(novoEmail);

        // Assert
        assertEquals(novoEmail, medico.getEmail(), "Email deve ser alterado");
    }

    @Test
    @DisplayName("Deve comparar médicos pela identidade")
    void deveCompararMedicosPelaIdentidade() {
        // Arrange
        Medico medico1 = criarMedico("Dr. João", "12345678900", "123456", "joao@example.com");
        Medico medico2 = medico1;

        // Act & Assert
        assertEquals(medico1, medico2, "Médicos com mesmo UUID devem ser iguais");
        assertEquals(medico1.hashCode(), medico2.hashCode(), "HashCode deve ser igual");
    }

    // ========== TESTES DE FALHA ==========

    @Test
    @DisplayName("Deve lançar exceção quando nome é nulo")
    void deveLancarExcecaoQuandoNomeEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Medico(null, new CPF("12345678900"), new CRM("123456", "SP"), new Email("medico@example.com")),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O nome do médico é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome é vazio")
    void deveLancarExcecaoQuandoNomeEhVazio() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Medico("   ", new CPF("12345678900"), new CRM("123456", "SP"), new Email("medico@example.com")),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O nome do médico é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF é nulo")
    void deveLancarExcecaoQuandoCPFEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Medico("Dr. João", null, new CRM("123456", "SP"), new Email("medico@example.com")),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O CPF do médico é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CRM é nulo")
    void deveLancarExcecaoQuandoCRMEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Medico("Dr. João", new CPF("12345678900"), null, new Email("medico@example.com")),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O CRM do médico é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email é nulo")
    void deveLancarExcecaoQuandoEmailEhNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Medico("Dr. João", new CPF("12345678900"), new CRM("123456", "SP"), null),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O email do médico é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao alterar nome para vazio")
    void deveLancarExcecaoAoAlterarNomeParaVazio() {
        // Arrange
        Medico medico = criarMedico("Dr. João", "12345678900", "123456", "joao@example.com");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> medico.alterarNome("   "),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O nome não pode ser vazio.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao alterar email para nulo")
    void deveLancarExcecaoAoAlterarEmailParaNulo() {
        // Arrange
        Medico medico = criarMedico("Dr. João", "12345678900", "123456", "joao@example.com");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> medico.alterarEmail(null),
            "Deve lançar IllegalArgumentException"
        );
        assertEquals("O email não pode ser vazio.", exception.getMessage());
    }

    @Test
    @DisplayName("Não deve considerar médicos diferentes como iguais")
    void naoDeveConsiderarMedicosDiferentesComoIguais() {
        // Arrange
        Medico medico1 = criarMedico("Dr. João", "12345678900", "123456", "joao@example.com");
        Medico medico2 = criarMedico("Dr. João", "12345678900", "123456", "joao@example.com");

        // Act & Assert
        assertNotEquals(medico1, medico2, "Médicos com IDs diferentes não devem ser iguais");
        assertNotEquals(medico1.hashCode(), medico2.hashCode(), "HashCode deve ser diferente");
    }

    private Medico criarMedico(String nome, String cpf, String crm, String email) {
        return new Medico(nome, new CPF(cpf), new CRM(crm, "SP"), new Email(email));
    }
}
