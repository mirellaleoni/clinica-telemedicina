package com.clinica.presentation;

import com.clinica.application.*;
import com.clinica.domain.Medico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final CadastrarMedicoUseCase cadastrarMedicoUseCase;
    private final ListarMedicosUseCase listarMedicosUseCase;
    private final BuscarMedicoUseCase buscarMedicoUseCase;
    private final AtualizarMedicoUseCase atualizarMedicoUseCase;
    private final DesativarMedicoUseCase desativarMedicoUseCase;

    public MedicoController(
            CadastrarMedicoUseCase cadastrarMedicoUseCase,
            ListarMedicosUseCase listarMedicosUseCase,
            BuscarMedicoUseCase buscarMedicoUseCase,
            AtualizarMedicoUseCase atualizarMedicoUseCase,
            DesativarMedicoUseCase desativarMedicoUseCase) {
        this.cadastrarMedicoUseCase = cadastrarMedicoUseCase;
        this.listarMedicosUseCase = listarMedicosUseCase;
        this.buscarMedicoUseCase = buscarMedicoUseCase;
        this.atualizarMedicoUseCase = atualizarMedicoUseCase;
        this.desativarMedicoUseCase = desativarMedicoUseCase;
    }

    @PostMapping
    public ResponseEntity<Medico> cadastrar(@RequestBody CadastrarMedicoRequest request) {
        Medico medico = cadastrarMedicoUseCase.executar(
                request.nome(),
                request.cpf(),
                request.crmNumero(),
                request.crmUf(),
                request.email()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(medico);
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listar() {
        return ResponseEntity.ok(listarMedicosUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable UUID id) {
        Medico medico = buscarMedicoUseCase.executar(id);
        return ResponseEntity.ok(medico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable UUID id, @RequestBody AtualizarMedicoRequest request) {
        atualizarMedicoUseCase.executar(id, request.nome(), request.email());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable UUID id) {
        desativarMedicoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    public record CadastrarMedicoRequest(String nome, String cpf, String crmNumero, String crmUf, String email) {}
    public record AtualizarMedicoRequest(String nome, String email) {}
}