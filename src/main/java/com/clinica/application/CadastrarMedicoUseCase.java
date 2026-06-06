package com.clinica.application;

import com.clinica.domain.Medico;
import com.clinica.domain.CPF;
import com.clinica.domain.CRM;
import com.clinica.domain.Email;
import com.clinica.infrastructure.MedicoEntity;
import com.clinica.infrastructure.MedicoRepository;
import org.springframework.stereotype.Service;
import java.util.UUID; // Adicionado para o tipo UUID do usuarioId

@Service
public class CadastrarMedicoUseCase {

    private final MedicoRepository repository;

    public CadastrarMedicoUseCase(MedicoRepository repository) {
        this.repository = repository;
    }

    // Adicionado UUID usuarioId como parâmetro do método executar
    public Medico executar(String nome, String cpf, String crmNumero, String crmUf, String email, String especialidade, Boolean ativo, UUID usuarioId) {
        Medico medico = new Medico(
            nome,
            new CPF(cpf),
            new CRM(crmNumero, crmUf),
            new Email(email)
        );

        // Agora passando os 8 parâmetros exigidos pelo novo construtor da MedicoEntity
        MedicoEntity entity = new MedicoEntity(
            medico.getId(),
            medico.getNome(),
            medico.getCpf().getNumero(),
            medico.getCrm().getValorCompleto(),
            medico.getEmail().getEndereco(),
            especialidade,
            ativo,
            usuarioId // <--- Passando o ID do usuário mapeado aqui
        );

        repository.save(entity);
        return medico;
    }
}