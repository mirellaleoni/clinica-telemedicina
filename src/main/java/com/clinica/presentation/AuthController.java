package com.clinica.presentation;

import com.clinica.application.AutenticarUsuarioUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    public AuthController(AutenticarUsuarioUseCase autenticarUsuarioUseCase) {
        this.autenticarUsuarioUseCase = autenticarUsuarioUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = autenticarUsuarioUseCase.executar(request.email(), request.senha());
            
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout realizado com sucesso. O token deve ser descartado pelo front-end.");
    }

    public record LoginRequest(String email, String senha) {}
    public record TokenResponse(String token) {}
}