package com.clinica.domain;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProntuarioTest {

    @Test
    void deveCriarProntuarioComSucesso() {
        UUID pacienteId = UUID.randomUUID();
        UUID medicoId = UUID.randomUUID();
        String descricao = "Paciente relata dores de cabeça e febre há 3 dias.";

        Prontuario prontuario = new Prontuario(pacienteId, medicoId, descricao);

        assertNotNull(prontuario.getId());
        assertEquals(pacienteId, prontuario.getPacienteId());
        assertEquals(medicoId, prontuario.getMedicoId());
        assertEquals(descricao, prontuario.getDescricao());
        assertNotNull(prontuario.getCriadoEm());
    }

    @Test
    void naoDeveCriarProntuarioSemDescricao() {
        UUID pacienteId = UUID.randomUUID();
        UUID medicoId = UUID.randomUUID();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(pacienteId, medicoId, "   ");
        });

        assertEquals("A descrição do prontuário é obrigatória.", exception.getMessage());
    }
    
    @Test
    void naoDeveCriarProntuarioSemIds() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(null, UUID.randomUUID(), "Descricao valida");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Prontuario(UUID.randomUUID(), null, "Descricao valida");
        });
    }
}