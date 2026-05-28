# Sistema de Agendamento e Telemedicina para Clínicas Populares

## O Problema

Agendar consultas médicas presenciais ou online, gerenciar salas/links de videoconferência, prontuários eletrônicos e receitas médicas digitais.

## Contextos do Sistema (DDD)

```
Agenda Médica          Atendimento/Prontuário      Faturamento
      ↓                        ↓                       ↓
Agendamentos           Consultas online/presencial  Pagamentos
Disponibilidade        Prontuário eletrônico        Planos/convênios
Notificações           Receitas digitais            Faturas
```

---

## Stack

| Camada | Tecnologia |
|---|---|
| Backend | Java + Spring Boot 3.5.14 |
| Banco de dados | PostgreSQL 16 |
| Migrations | Flyway |
| Containers | Docker / Podman |
| Visualização do banco | DBeaver |
| Frontend | React |
| Autenticação | JWT |
| Build | Gradle |

---

## Estrutura de Pacotes (DDD)

```
com.clinica/
├── agenda/
│   ├── domain/           ← entidades e regras de negócio
│   ├── application/      ← casos de uso
│   ├── infrastructure/   ← repositórios e integrações
│   └── api/              ← controllers e DTOs
├── atendimento/
│   ├── domain/
│   ├── application/
│   ├── infrastructure/
│   └── api/
├── faturamento/
│   ├── domain/
│   ├── application/
│   ├── infrastructure/
│   └── api/
└── shared/
    ├── domain/           ← entidades compartilhadas (Paciente, Usuario)
    └── security/         ← JWT e autenticação
```

---

## Rotas da API

### Pacientes
```
GET    /pacientes              → lista todos
GET    /pacientes/{id}         → busca um
GET    /pacientes/search?q=    → busca por nome
POST   /pacientes              → cadastra
PUT    /pacientes/{id}         → atualiza
DELETE /pacientes/{id}         → remove
```

### Agenda
```
GET    /medicos                      → lista médicos
GET    /medicos/{id}/disponibilidade → horários disponíveis
POST   /agendamentos                 → cria agendamento
GET    /agendamentos/{id}            → busca agendamento
PUT    /agendamentos/{id}            → atualiza
DELETE /agendamentos/{id}            → cancela
```

### Atendimento
```
POST   /consultas/{id}/iniciar       → inicia consulta
GET    /consultas/{id}/link          → link da videochamada
POST   /prontuarios                  → cria prontuário
GET    /prontuarios/{id}             → busca prontuário
PUT    /prontuarios/{id}             → atualiza
POST   /receitas                     → emite receita digital
GET    /receitas/{id}                → busca receita
```

### Faturamento
```
POST   /pagamentos                   → registra pagamento
GET    /pagamentos/{id}              → busca pagamento
GET    /faturas/{pacienteId}         → lista faturas do paciente
GET    /relatorios/financeiro        → relatório financeiro
```

### Autenticação
```
POST   /auth/login                   → login, retorna JWT
POST   /auth/logout                  → logout
```

---

## Plano de Ação por Fases

### Fase 1 — Fundação
- [x] Projeto Spring Boot criado
- [x] Docker + PostgreSQL
- [ ] Flyway funcionando
- [ ] Estrutura de pacotes DDD
- [ ] Entidade Paciente

### Fase 2 — Contexto de Agenda
- [ ] Cadastro de médicos
- [ ] Disponibilidade de horários
- [ ] Agendamento presencial
- [ ] Agendamento online
- [ ] Notificações (email)

### Fase 3 — Contexto de Atendimento
- [ ] Consulta presencial
- [ ] Videochamada (link/sala)
- [ ] Prontuário eletrônico
- [ ] Receita médica digital
- [ ] Histórico do paciente

### Fase 4 — Contexto de Faturamento
- [ ] Planos e convênios
- [ ] Pagamento da consulta
- [ ] Geração de faturas
- [ ] Relatórios financeiros

### Fase 5 — Segurança e Finalização
- [ ] Autenticação JWT
- [ ] Controle de acesso por perfil (admin, médico, paciente)
- [ ] LGPD — isolamento dados do paciente (Bounded Context)
- [ ] Testes unitários e de integração
- [ ] Deploy

---

## Modelagem do Banco

### Contexto: Agenda
```sql
medicos (id, usuario_id, crm, especialidade, ativo)
disponibilidades (id, medico_id, dia_semana, hora_inicio, hora_fim)
agendamentos (id, paciente_id, medico_id, data_hora, tipo, status)
```

### Contexto: Atendimento
```sql
consultas (id, agendamento_id, link_video, iniciada_em, finalizada_em)
prontuarios (id, paciente_id, medico_id, descricao, criado_em)
receitas (id, consulta_id, medicamentos, instrucoes, emitida_em)
```

### Contexto: Faturamento
```sql
pagamentos (id, agendamento_id, valor, forma_pagamento, status)
faturas (id, paciente_id, total, vencimento, status)
convenios (id, nome, cobertura)
```

### Compartilhado
```sql
usuarios (id, nome, email, senha_hash, role, criado_em)
pacientes (id, nome, data_nascimento, telefone, observacoes, criado_em)
```

---

## Próximos Passos Imediatos

1. Resolver o Flyway
2. Criar a estrutura de pacotes DDD
3. Criar as migrations do banco
4. Implementar CRUD de pacientes
5. Implementar autenticação JWT