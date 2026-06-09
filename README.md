# Sistema de Agendamento e Telemedicina para Clínicas Populares

> **Projeto Prático Integrador: Engenharia de Software e Orientação a Objetos**
> Tema 5: Sistema de Agendamento e Telemedicina para Clínicas Populares

Este repositório contém o Núcleo de Domínio (Core Domain) de uma aplicação de telemedicina e agendamentos clínicos, desenvolvido como requisito para o Projeto Prático Integrador. A aplicação permite o agendamento de consultas (presenciais ou online), o gerenciamento de prontuários eletrônicos e a emissão de receitas médicas digitais.

---

## 👥 Equipe

* **Mirella Leoni** - [@mirellaleoni](https://github.com/mirellaleoni)
* **Kauã Xavier** - [@Kaua032](https://github.com/Kaua032)
* **Naomi Nino** - [@iuvieun](https://github.com/iuvieun)

**Curso:** Ciência da Computação
**Disciplina:** Projeto de Programação

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java (JDK 21+)
* **Framework Backend:** Spring Boot 3.x
* **Gerenciador de Dependências:** Gradle
* **Testes TDD:** JUnit 5 + Mockito
* **Banco de Dados:** PostgreSQL 16
* **Migrations:** Flyway
* **Containers:** Docker / Docker Compose
* **CI/CD:** GitHub Actions

---

## 🏗️ Arquitetura e Padrões (OO & DDD)

O projeto foi rigorosamente desenhado seguindo os princípios de **Domain-Driven Design (DDD)** e **Orientação a Objetos Avançada**, com clara separação de responsabilidades.

### Divisão em Bounded Contexts:
1. **Agenda Médica:** Gerencia agendamentos, médicos e disponibilidades.
2. **Atendimento:** Lida com a realização da consulta, geração de prontuários e receitas.
3. **Faturamento:** (Parcial) Controle de pagamentos e faturas.

### Padrões Aplicados no Domínio (`src/domain/`):
* **Entidades:** Objetos com identidade única, como `Paciente`, `Medico`, `Prontuario` e `Receita`.
* **Value Objects (Objetos de Valor):** Objetos imutáveis e sem identidade, utilizados para garantir integridade, como `CPF`, `Email`, `CRM`, e `HorarioDisponivel`.
* **Aggregates e Aggregate Roots:** Objetos que atuam como porta de entrada e garantem a consistência transacional do conjunto. Por exemplo, `Agendamento` é o Aggregate Root para validar disponibilidade de agenda, e `Consulta` orquestra prontuário e link da chamada.
* **Encapsulamento Rigoroso:** Os atributos são estritamente privados e modificados apenas por métodos que representam a linguagem ubíqua do negócio, sem o uso de "setters" anêmicos.

### Padrão de Projeto
* A arquitetura adota **Clean Architecture / Hexagonal**, onde o Core Domain não tem nenhuma dependência de frameworks externos ou banco de dados.

---

## ⚙️ Bônus Implementado (+10 Pontos)

Além do escopo do domínio, este projeto implementa a **Camada de Apresentação (`presentation`) e Infraestrutura (`infrastructure`)**, integradas a um banco de dados relacional (PostgreSQL).

* **Controllers RESTful:** Na camada `presentation/`, expondo as rotas necessárias para uso da aplicação, chamando os casos de uso da camada `application/`.
* **Persistência Real:** As entidades e agregados do domínio são persistidas via repositórios na camada `infrastructure/`, implementando o fluxo completo de uma aplicação real.

### 🖥️ Front-end / Interface Gráfica (Web)
Para garantir a pontuação extra do desafio (Plus), o sistema conta com uma interface gráfica conectada a este backend. O Front-end consome as rotas REST disponibilizadas pelos nossos controllers, permitindo que usuários interajam diretamente com o sistema de agendamentos e prontuários, garantindo o ciclo completo de persistência real e rastreabilidade no PostgreSQL.

---

## 🧪 Desenvolvimento Guiado por Testes (TDD)

Todo o núcleo da aplicação (`src/domain/`) foi escrito adotando a prática de **TDD (Test-Driven Development)**, cobrindo cenários de sucesso e caminhos de falha (ex: validação de CPF, validação de regras de negócio de agendamento). 

Os testes podem ser encontrados na pasta `src/test/java/com/clinica/domain/`.

---

## 🛠️ Como Executar o Projeto

### Pré-requisitos
* Java 21+ instalado
* Docker e Docker Compose instalados

### Passo a Passo

1. **Clone o repositório**
```bash
git clone https://github.com/mirellaleoni/clinica-telemedicina.git
cd clinica-telemedicina
```

2. **Suba os serviços de infraestrutura (Banco de Dados)**
```bash
docker-compose up -d
```

3. **Execute os testes (Para validar o TDD)**
```bash
./gradlew test
```

4. **Inicie a aplicação Backend**
```bash
./gradlew bootRun
```
A API estará disponível na porta configurada (geralmente `http://localhost:8080`). As tabelas do banco serão criadas automaticamente pelas migrations do Flyway.

5. **Inicie o Front-end**
O código da interface gráfica está localizado dentro do próprio repositório. Navegue até a pasta do front-end e inicie a aplicação com os comandos abaixo:
```bash
cd src/main/java/com/clinica/presentation/web-ui
npm install
npm run dev   # ou npm start
```
*(Certifique-se de configurar as variáveis de ambiente do front-end para apontar para a API em `http://localhost:8080`)*

---

## 🔄 Integração Contínua (CI/CD)

O projeto conta com um pipeline automatizado usando **GitHub Actions**. A cada novo commit ou Pull Request para a branch principal, a esteira (`ci.yml`) é acionada para garantir que:
- O build com o Gradle ocorra sem erros de compilação.
- Todos os testes unitários (escritos na fase de TDD) passem com sucesso (Green).
