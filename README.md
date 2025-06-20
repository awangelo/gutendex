# Gutendex API Challenge

Este é um projeto de desafio que consome a API Gutendex para buscar livros e autores e persistir os dados em um banco de dados PostgreSQL.

## Funcionalidades

*   Buscar livro por título na API Gutendex.
*   Listar todos os livros registrados no banco de dados.
*   Listar todos os autores registrados no banco de dados.
*   Listar autores vivos em um determinado ano.
*   Listar livros em um determinado idioma.
*   Exibir estatísticas sobre a quantidade de livros por idioma.

## Tecnologias Utilizadas

*   Java 21
*   Spring Boot
*   Spring Data JPA
*   PostgreSQL
*   Maven
*   Jackson

## Como Executar

### Pré-requisitos

*   JDK 21 ou superior
*   Maven
*   PostgreSQL

### Configuração do Banco de Dados

1.  Crie um banco de dados no PostgreSQL chamado `gutendex`.
2.  Atualize as credenciais do banco de dados no arquivo `src/main/resources/application.properties` se necessário.

### Executando a Aplicação

1.  Clone o repositório.
2.  Abra um terminal na pasta raiz do projeto.
3.  Execute o seguinte comando para rodar a aplicação:

```bash
./mvnw spring-boot:run
```

A aplicação iniciará e você verá um menu de opções no console.

## Referência da API