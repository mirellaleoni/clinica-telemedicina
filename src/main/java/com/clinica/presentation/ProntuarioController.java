package com.clinica.presentation;

import com.clinica.application.BuscarProntuarioUseCase;
import com.clinica.application.CriarProntuarioUseCase;
import com.clinica.domain.Prontuario;
import com.clinica.infrastructure.ProntuarioEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    private final CriarProntuarioUseCase criarProntuarioUseCase;
    private final BuscarProntuarioUseCase buscarProntuarioUseCase;

    public ProntuarioController(
            CriarProntuarioUseCase criarProntuarioUseCase,
            BuscarProntuarioUseCase buscarProntuarioUseCase) {
        this.criarProntuarioUseCase = criarProntuarioUseCase;
        this.buscarProntuarioUseCase = buscarProntuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<Prontuario> criar(@RequestBody CriarProntuarioRequest request) {
        Prontuario prontuario = criarProntuarioUseCase.executar(
            request.consultaId(),
            request.observacoes(),
            request.prescricao()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(prontuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioEntity> buscar(@PathVariable UUID id) {
        ProntuarioEntity prontuario = buscarProntuarioUseCase.executar(id);
        return ResponseEntity.ok(prontuario);
    }

    public record CriarProntuarioRequest(UUID consultaId, String observacoes, String prescricao) {}
}