package com.clinica.presentation;

import com.clinica.application.CadastrarPacienteUseCase;
import com.clinica.application.ListarPacientesUseCase;

import com.clinica.infrastructure.PacienteEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final CadastrarPacienteUseCase cadastrarPacienteUseCase;
    private final ListarPacientesUseCase listarPacientesUseCase;

    public PacienteController(CadastrarPacienteUseCase cadastrarPacienteUseCase, ListarPacientesUseCase listarPacientesUseCase) {
        this.cadastrarPacienteUseCase = cadastrarPacienteUseCase;
        this.listarPacientesUseCase = listarPacientesUseCase;
    }

    @PostMapping
    public ResponseEntity<PacienteEntity> cadastrar(@RequestBody CadastrarPacienteRequest request) {

        LocalDate dataNascimento = LocalDate.parse(request.dataNascimento());
        
        PacienteEntity pacienteSalvo = cadastrarPacienteUseCase.executar(
                request.nome(),
                request.cpf(),
                request.email(),
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

    public record CadastrarPacienteRequest(String nome, String cpf, String email, String dataNascimento, String telefone) {}
}