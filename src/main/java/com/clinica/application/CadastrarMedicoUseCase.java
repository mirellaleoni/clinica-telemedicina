package com.clinica.application;

import com.clinica.domain.Medico;
import com.clinica.domain.CPF;
import com.clinica.domain.CRM;
import com.clinica.domain.Email;
import com.clinica.infrastructure.MedicoEntity;
import com.clinica.infrastructure.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastrarMedicoUseCase {

    private final MedicoRepository repository;

    public CadastrarMedicoUseCase(MedicoRepository repository) {
        this.repository = repository;
    }

    public Medico executar(String nome, String cpf, String crmNumero, String crmUf, String email) {
        Medico medico = new Medico(
            nome,
            new CPF(cpf),
            new CRM(crmNumero, crmUf),
            new Email(email)
        );

        MedicoEntity entity = new MedicoEntity(
            medico.getId(),
            medico.getNome(),
            medico.getCpf().getNumero(),
            medico.getCrm().getValorCompleto(),
            medico.getEmail().getEndereco()
        );

        repository.save(entity);
        return medico;
    }
}