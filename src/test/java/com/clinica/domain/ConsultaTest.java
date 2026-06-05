package com.clinica.domain;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaTest {

    @Test
    void deveIniciarConsultaComSucesso() {
        UUID agendamentoId = UUID.randomUUID();
        LinkVideochamada link = new LinkVideochamada("https://telemedicina.com/sala/123");

        Consulta consulta = new Consulta(agendamentoId, link);

        assertNotNull(consulta.getId());
        assertEquals(agendamentoId, consulta.getAgendamentoId());
        assertNotNull(consulta.getIniciadaEm());
        assertNull(consulta.getFinalizadaEm());
    }

    @Test
    void deveFinalizarConsultaCorretamente() {
        Consulta consulta = new Consulta(UUID.randomUUID(), new LinkVideochamada("https://link.com"));
        
        consulta.finalizar();

        assertNotNull(consulta.getFinalizadaEm());
        assertTrue(consulta.getFinalizadaEm().isAfter(consulta.getIniciadaEm()));
    }
}