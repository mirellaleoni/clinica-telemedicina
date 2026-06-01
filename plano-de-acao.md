``# Sistema de Agendamento e Telemedicina para Clínicas Populares

## O Problema

Agendar consultas médicas presenciais ou online, gerenciar salas e links de videoconferência, prontuários eletrônicos e receitas médicas digitais.

---

## Equipe

| Nome | GitHub |
|---|---|
| Mirella Leoni | @mirellaleoni |
| Nome Aluno 2 | @user2 |

> Lembrete: todos os integrantes precisam fazer commits. A IA cruza o `project-meta.json` com o histórico do Git.

---

## Stack

| Camada | Tecnologia |
|---|---|
| Backend | Java + Spring Boot 3.5.14 |
| Banco de dados | PostgreSQL 16 |
| Migrations | Flyway |
| Containers | Docker |
| Visualização do banco | DBeaver |
| Autenticação | JWT |
| Build | Gradle |
| Testes | JUnit 5 + Mockito |
| CI/CD | GitHub Actions |

---

## Critérios de Avaliação

| Critério | O que a IA verifica | Pontuação |
|---|---|---|
| CI/CD | Arquivo `.github/workflows/ci.yml` + build verde | 10pts |
| Dependências | `build.gradle` limpo com deps de teste mapeadas | 10pts |
| Testes TDD | Testes em `src/domain/` com cenários de sucesso e falha | 20pts |
| OO & DDD | Encapsulamento + Value Objects + lógica APENAS em `domain/` | 20pts |
| Git em equipe | Commits e PRs equilibrados entre os integrantes | 20pts |
| **Bônus** | `presentation/` chamando `domain/` com persistência real | +10pts |

---

## Conceitos DDD obrigatórios no código

### Entidades — objetos com identidade própria
- `Paciente` — identificado pelo `id`
- `Medico` — identificado pelo `id`
- `Agendamento` — identificado pelo `id`
- `Consulta` — identificada pelo `id`
- `Prontuario` — identificado pelo `id`

### Value Objects — imutáveis, sem identidade
- `CPF` — valida o formato, atributos `final`
- `Email` — valida o formato, atributos `final`
- `CRM` — valida o formato, atributos `final`
- `HorarioDisponivel` — dia + hora, imutável
- `LinkVideochamada` — URL da sala, imutável

### Aggregate Roots — garantem consistência do grupo
- `Agendamento` é o Aggregate Root do contexto de Agenda
  - Controla: `Medico`, `Paciente`, `HorarioDisponivel`
  - Regra: não permite agendar em horário ocupado
- `Consulta` é o Aggregate Root do contexto de Atendimento
  - Controla: `Prontuario`, `Receita`, `LinkVideochamada`
- `Pagamento` é o Aggregate Root do contexto de Faturamento

### Bounded Contexts — isolamento entre contextos
```
Agenda Médica       → Agendamento, Medico, HorarioDisponivel
Atendimento         → Consulta, Prontuario, Receita
Faturamento         → Pagamento, Fatura, Convenio
Compartilhado       → Paciente, Usuario, Email, CPF
```

---

## Estrutura de Pastas

> A IA avaliadora procura especificamente pelas pastas `src/domain/` e `src/presentation/`

```
clinica-telemedicina/
├── .github/
│   └── workflows/
│       └── ci.yml                    ← obrigatório para CI/CD
├── src/
│   ├── main/java/com/clinica/
│   │   ├── domain/                   ← IA avalia testes AQUI
│   │   │   ├── Paciente.java         ← Entidade
│   │   │   ├── Medico.java           ← Entidade
│   │   │   ├── Agendamento.java      ← Aggregate Root
│   │   │   ├── Consulta.java         ← Aggregate Root
│   │   │   ├── Prontuario.java       ← Entidade
│   │   │   ├── CPF.java              ← Value Object
│   │   │   ├── Email.java            ← Value Object
│   │   │   ├── CRM.java              ← Value Object
│   │   │   └── HorarioDisponivel.java← Value Object
│   │   ├── presentation/             ← bônus +10pts
│   │   │   ├── PacienteController.java
│   │   │   ├── AgendamentoController.java
│   │   │   └── ConsultaController.java
│   │   ├── application/
│   │   │   ├── CriarAgendamentoUseCase.java
│   │   │   ├── IniciarConsultaUseCase.java
│   │   │   └── CadastrarPacienteUseCase.java
│   │   └── infrastructure/
│   │       ├── PacienteRepository.java
│   │       └── AgendamentoRepository.java
│   └── test/java/com/clinica/
│       └── domain/                   ← testes obrigatórios
│           ├── CPFTest.java
│           ├── EmailTest.java
│           ├── AgendamentoTest.java
│           └── PacienteTest.java
├── src/main/resources/
│   ├── application.properties
│   └── db/migration/
│       ├── V0__enable_extensions.sql
│       ├── V1__create_usuarios.sql
│       ├── V2__create_pacientes.sql
│       ├── V3__create_agenda.sql
│       └── V4__create_atendimento.sql
├── project-meta.json                 ← obrigatório para avaliação
├── build.gradle
├── podman-compose.yml
└── README.md
```

---

## Regras de OO obrigatórias

- Todos os atributos das entidades devem ser `private`
- Acesso apenas via métodos (`getters` restritos, sem `setters` públicos desnecessários)
- Value Objects com todos os atributos `final` (imutáveis)
- Lógica de negócio NUNCA nos controllers — sempre no `domain/`
- Composição sobre herança — `Agendamento` contém `Paciente`, não herda

---

## Regras de TDD obrigatórias

O histórico de commits precisa mostrar o ciclo:

```
1. Commit: "test: add CPFTest - valida CPF inválido"   ← Red
2. Commit: "feat: implementa validação CPF"             ← Green
3. Commit: "refactor: melhora legibilidade CPF"         ← Refactor
```

Cada classe do `domain/` precisa de:
- Teste de cenário de **sucesso**
- Teste de cenário de **falha**

---

## Modelagem do Banco

### Compartilhado
```
usuarios    → id, nome, email, senha_hash, role, criado_em
pacientes   → id, nome, data_nascimento, telefone, cpf, criado_em
```

### Contexto: Agenda
```
medicos          → id, usuario_id, crm, especialidade, ativo
disponibilidades → id, medico_id, dia_semana, hora_inicio, hora_fim
agendamentos     → id, paciente_id, medico_id, data_hora, tipo, status
```

### Contexto: Atendimento
```
consultas   → id, agendamento_id, link_video, iniciada_em, finalizada_em
prontuarios → id, paciente_id, medico_id, descricao, criado_em
receitas    → id, consulta_id, medicamentos, instrucoes, emitida_em
```

### Contexto: Faturamento
```
pagamentos → id, agendamento_id, valor, forma_pagamento, status
faturas    → id, paciente_id, total, vencimento, status
convenios  → id, nome, cobertura
```

---

## Rotas da API

### Autenticação
```
POST /auth/login    → login, retorna JWT
POST /auth/logout   → logout
```

### Pacientes
```
GET    /pacientes           → lista todos
GET    /pacientes/{id}      → busca um
GET    /pacientes/search?q= → busca por nome
POST   /pacientes           → cadastra
PUT    /pacientes/{id}      → atualiza
DELETE /pacientes/{id}      → remove
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
POST /consultas/{id}/iniciar → inicia consulta
GET  /consultas/{id}/link    → link da videochamada
POST /prontuarios            → cria prontuário
GET  /prontuarios/{id}       → busca prontuário
POST /receitas               → emite receita digital
GET  /receitas/{id}          → busca receita
```

### Faturamento
```
POST /pagamentos             → registra pagamento
GET  /pagamentos/{id}        → busca pagamento
GET  /faturas/{pacienteId}   → lista faturas
GET  /relatorios/financeiro  → relatório financeiro
```

---

## Plano de Ação por Fases

### Fase 1 — Fundação
- [x] Projeto Spring Boot criado
- [x] Docker + PostgreSQL
- [x] Flyway funcionando
- [x] `project-meta.json` na raiz
- [x] `.github/workflows/ci.yml` configurado
- [x] Estrutura de pastas criada

### Fase 2 — Domain (TDD)
- [ ] Value Object `CPF` + teste
- [x] Value Object `Email` + teste
- [x] Value Object `CRM` + teste
- [ ] Value Object `HorarioDisponivel` + teste
- [x] Entidade `Paciente` + teste
- [ ] Entidade `Medico` + teste
- [ ] Aggregate Root `Agendamento` + teste
- [ ] Aggregate Root `Consulta` + teste
- [ ] Entidade `Prontuario` + teste

### Fase 3 — Application
- [ ] `CadastrarPacienteUseCase`
- [ ] `CriarAgendamentoUseCase`
- [ ] `IniciarConsultaUseCase`

### Fase 4 — Presentation (bônus +10pts)
- [ ] `PacienteController`
- [ ] `AgendamentoController`
- [ ] `ConsultaController`
- [ ] `AuthController`

### Fase 5 — Infrastructure
- [ ] Migrations Flyway completas
- [ ] Repositories JPA
- [ ] Configuração JWT

### Fase 6 — Finalização
- [x] CI/CD passando no GitHub (build verde)
- [ ] Commits equilibrados entre os tres integrantes
- [ ] README atualizado