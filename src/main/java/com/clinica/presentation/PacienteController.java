package com.clinica.presentation;

import com.clinica.application.*;
import com.clinica.infrastructure.PacienteEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final CadastrarPacienteUseCase cadastrarPacienteUseCase;
    private final ListarPacientesUseCase listarPacientesUseCase;
    private final BuscarPacienteUseCase buscarPacienteUseCase;
    private final BuscarPacientesPorNomeUseCase buscarPacientesPorNomeUseCase;
    private final AtualizarPacienteUseCase atualizarPacienteUseCase;
    private final RemoverPacienteUseCase removerPacienteUseCase;

    public PacienteController(
            CadastrarPacienteUseCase cadastrarPacienteUseCase,
            ListarPacientesUseCase listarPacientesUseCase,
            BuscarPacienteUseCase buscarPacienteUseCase,
            BuscarPacientesPorNomeUseCase buscarPacientesPorNomeUseCase,
            AtualizarPacienteUseCase atualizarPacienteUseCase,
            RemoverPacienteUseCase removerPacienteUseCase) {
        this.cadastrarPacienteUseCase = cadastrarPacienteUseCase;
        this.listarPacientesUseCase = listarPacientesUseCase;
        this.buscarPacienteUseCase = buscarPacienteUseCase;
        this.buscarPacientesPorNomeUseCase = buscarPacientesPorNomeUseCase;
        this.atualizarPacienteUseCase = atualizarPacienteUseCase;
        this.removerPacienteUseCase = removerPacienteUseCase;
    }

    @PostMapping
    public ResponseEntity<PacienteEntity> cadastrar(@RequestBody CadastrarPacienteRequest request) {

        LocalDate dataNascimento = LocalDate.parse(request.dataNascimento());
        
        PacienteEntity pacienteSalvo = cadastrarPacienteUseCase.executar(
                request.nome(),
                request.cpf(),
                dataNascimento,
                request.telefone()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<PacienteEntity>> listar() {
        List<PacienteEntity> pacientes = listarPacientesUseCase.executar();

        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<PacienteEntity> buscarPorId(@PathVariable String id) {
        return buscarPacienteUseCase.executar(UUID.fromString(id)).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<PacienteEntity>> buscarPorNome(@RequestParam("q") String q) {
        List<PacienteEntity> pacientes = buscarPacientesPorNomeUseCase.executar(q);
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("{id}")
    public ResponseEntity<PacienteEntity> atualizar(@PathVariable UUID id, @RequestBody AtualizarPacienteRequest request) {
        PacienteEntity pacienteAtualizado = atualizarPacienteUseCase.executar(
                id, request.nome(), request.telefone()
        );
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        removerPacienteUseCase.executar(id);
        return ResponseEntity.noContent().build(); 
    }

    public record CadastrarPacienteRequest(String nome, String cpf, String dataNascimento, String telefone) {}
    public record AtualizarPacienteRequest(String nome, String telefone) {}
}