package com.clinica.application;

import com.clinica.domain.CPF;
import com.clinica.domain.Email;
import com.clinica.domain.Paciente;
import com.clinica.infrastructure.PacienteEntity;
import com.clinica.infrastructure.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CadastrarPacienteUseCase {

    private final PacienteRepository repository;

    public CadastrarPacienteUseCase(PacienteRepository repository) {
        this.repository = repository;
    }

    public PacienteEntity executar(String nome, String cpfTexto, String emailTexto) {
        
        CPF cpf = new CPF(cpfTexto);
        Email email = new Email(emailTexto);

        Paciente paciente = new Paciente(nome, cpf, email);

        PacienteEntity entity = new PacienteEntity(
                paciente.getId(),
                paciente.getNome(),
                null,
                null,
                paciente.getCpf().getNumero(),
                LocalDateTime.now()
        );

        return repository.save(entity);
    }
}