# Projeto CRUD de Alunos - Back-end (Spring Boot)

Este projeto implementa a API REST para o sistema de gerenciamento de alunos, desenvolvido com Spring Boot, Java 17 e Maven.

## Estrutura do Projeto

O back-end está organizado da seguinte forma:

- `src/main/java/com/example/alunoapi`
  - `config`: Configurações de CORS.
  - `controller`: Controlador REST (`AlunoController`) que expõe os endpoints da API.
  - `dto`: Data Transfer Objects (ex: `ApiResponse`).
  - `exception`: Tratamento global de exceções (`GlobalExceptionHandler`).
  - `model`: Entidade JPA (`Aluno`).
  - `repository`: Repositório Spring Data JPA (`AlunoRepository`).
  - `service`: Lógica de negócio (`AlunoService`).
- `src/main/resources`
  - `application.properties`: Arquivo de configuração da aplicação, incluindo conexão com o banco de dados.

## Requisitos

- Java 17
- Maven 3.6+
- MySQL 8.0+

## Como Executar

1. **Configurar o Banco de Dados**
   - Certifique-se de que o MySQL está em execução.
   - Crie um banco de dados chamado `aluno_db`.
   - As configurações de conexão (URL, usuário, senha) podem ser ajustadas no arquivo `src/main/resources/application.properties`.

2. **Compilar e Executar a Aplicação**
   - Navegue até a pasta `back-end`.
   - Execute o comando Maven para iniciar a aplicação:
     ```bash
     mvn spring-boot:run
     ```
   - A API estará disponível em `http://localhost:8080`.

## Endpoints da API

A API REST fornece os seguintes endpoints para o CRUD de alunos:

| Método | URL                  | Descrição                               |
|--------|----------------------|-------------------------------------------|
| `GET`    | `/api/alunos`        | Lista todos os alunos.                    |
| `GET`    | `/api/alunos/{id}`   | Busca um aluno pelo ID.                   |
| `POST`   | `/api/alunos`        | Cria um novo aluno.                       |
| `PUT`    | `/api/alunos/{id}`   | Atualiza um aluno existente.              |
| `DELETE` | `/api/alunos/{id}`   | Exclui um aluno.                          |
| `GET`    | `/api/alunos/buscar` | Busca alunos por nome ou curso (ex: `?termo=...`). |

## Validações

- **Nome**: Obrigatório e deve conter mais de 2 caracteres.
- **Curso**: Obrigatório e deve conter mais de 2 caracteres.
- **Telefone**: Opcional, mas se fornecido, deve seguir o formato de 10 ou 11 dígitos.

