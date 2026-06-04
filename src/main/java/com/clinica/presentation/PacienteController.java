package com.clinica.presentation;

import com.clinica.application.CadastrarPacienteUseCase;
import com.clinica.infrastructure.PacienteEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final CadastrarPacienteUseCase cadastrarPacienteUseCase;

    public PacienteController(CadastrarPacienteUseCase cadastrarPacienteUseCase) {
        this.cadastrarPacienteUseCase = cadastrarPacienteUseCase;
    }

    @PostMapping
    public ResponseEntity<PacienteEntity> cadastrar(@RequestBody CadastrarPacienteRequest request) {
        
        PacienteEntity pacienteSalvo = cadastrarPacienteUseCase.executar(
                request.nome(),
                request.cpf(),
                request.email()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }

    public record CadastrarPacienteRequest(String nome, String cpf, String email) {}
}