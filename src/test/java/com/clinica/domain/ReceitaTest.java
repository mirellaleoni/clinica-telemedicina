package com.clinica.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ReceitaTest {

    @Test
    @DisplayName("Deve criar receita com dados válidos")
    void deveCriarReceitaComSucesso() {
        UUID consultaId = UUID.randomUUID();
        Receita receita = new Receita(consultaId, "Dipirona 500mg", "Tomar de 8 em 8 horas");

        assertNotNull(receita.getId());
        assertEquals(consultaId, receita.getConsultaId());
        assertEquals("Dipirona 500mg", receita.getMedicamentos());
        assertNotNull(receita.getEmitidaEm());
    }

    @Test
    @DisplayName("Não deve criar receita com medicamentos em branco")
    void naoDeveCriarComMedicamentosVazios() {
        UUID consultaId = UUID.randomUUID();
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Receita(consultaId, "", "Tomar com água");
        });
        
        assertEquals("Os medicamentos não podem estar vazios.", exception.getMessage());
    }
}