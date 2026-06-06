package com.clinica.presentation;

import com.clinica.application.CadastrarUsuarioUseCase;
import com.clinica.domain.PapelUsuario;
import com.clinica.infrastructure.UsuarioEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    public UsuarioController(CadastrarUsuarioUseCase cadastrarUsuarioUseCase) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody CadastroUsuarioRequest request) {
        try {
            UsuarioEntity novoUsuario = cadastrarUsuarioUseCase.executar(
                    request.nome(),
                    request.email(),
                    request.senha(),
                    request.papel()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public record CadastroUsuarioRequest(String nome, String email, String senha, PapelUsuario papel) {}
}