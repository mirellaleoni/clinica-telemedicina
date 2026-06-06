package com.clinica.application;

import com.clinica.infrastructure.UsuarioEntity;

public interface TokenService {
    String gerarToken(UsuarioEntity usuario);
    String getSubject(String token);
}