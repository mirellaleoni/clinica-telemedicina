package com.clinica.presentation;

import com.clinica.application.BuscarReceitaUseCase;
import com.clinica.application.EmitirReceitaUseCase;
import com.clinica.infrastructure.ReceitaEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    private final EmitirReceitaUseCase emitirReceitaUseCase;
    private final BuscarReceitaUseCase buscarReceitaUseCase;

    public ReceitaController(EmitirReceitaUseCase emitirReceitaUseCase, BuscarReceitaUseCase buscarReceitaUseCase) {
        this.emitirReceitaUseCase = emitirReceitaUseCase;
        this.buscarReceitaUseCase = buscarReceitaUseCase;
    }

    @PostMapping
    public ResponseEntity<?> emitir(@RequestBody EmitirReceitaRequest request) {
        try {
            ReceitaEntity receita = emitirReceitaUseCase.executar(
                    request.consultaId(), request.medicamentos(), request.instrucoes()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(receita);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaEntity> buscarPorId(@PathVariable UUID id) {
        try {
            ReceitaEntity receita = buscarReceitaUseCase.executar(id);
            return ResponseEntity.ok(receita);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public record EmitirReceitaRequest(UUID consultaId, String medicamentos, String instrucoes) {}
}