package com.clinica.presentation;

import com.clinica.application.BuscarConsultaUseCase;
import com.clinica.application.FinalizarConsultaUseCase;
import com.clinica.application.IniciarConsultaUseCase;
import com.clinica.infrastructure.ConsultaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final BuscarConsultaUseCase buscarConsultaUseCase;
    private final IniciarConsultaUseCase iniciarConsultaUseCase;
    private final FinalizarConsultaUseCase finalizarConsultaUseCase;

    public ConsultaController(BuscarConsultaUseCase buscarConsultaUseCase,
                              IniciarConsultaUseCase iniciarConsultaUseCase,
                              FinalizarConsultaUseCase finalizarConsultaUseCase) {
        this.buscarConsultaUseCase = buscarConsultaUseCase;
        this.iniciarConsultaUseCase = iniciarConsultaUseCase;
        this.finalizarConsultaUseCase = finalizarConsultaUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaEntity> buscar(@PathVariable UUID id) {
        try {
            ConsultaEntity consulta = buscarConsultaUseCase.executar(id);
            return ResponseEntity.ok(consulta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<?> iniciar(@PathVariable UUID id) {
        try {
            ConsultaEntity consulta = iniciarConsultaUseCase.executar(id);
            return ResponseEntity.ok(consulta);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizar(@PathVariable UUID id) {
        try {
            ConsultaEntity consulta = finalizarConsultaUseCase.executar(id);
            return ResponseEntity.ok(consulta);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}