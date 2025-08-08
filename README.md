# Bookstore API

API de gerenciamento de livros para uma livraria usando Spring Boot.

## Descrição

Projeto de exemplo apresentado no curso de Pós-graduação de Análise e Desenvolvimento Java da FIAP.

## Tecnologias utilizadas

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- H2 Database
- OpenAPI Specification
- Problem Detail Specification
- Swagger Codegen
- Lombok
- Jakarta Validation

## Pré-requisitos

- Java 21
- Maven 3.9

## Instalação

Instruções sobre como configurar o projeto localmente.

```bash
# Clone o repositório
git clone https://github.com/sonnesen/bookstore.git

# Navegue até o diretório do projeto
cd bookstore

# Instale as dependências do projeto
mvn clean install
```

# Execução

```bash
# Para executar o projeto, execute o seguinte comando:
mvn spring-boot:run
```

## Documentação da API

Acesse a documentação no Swagger:

- [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Banco de dados

Acesse o console do H2:

- [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - JDBC URL: jdbc:h2:mem:bookstore
  - User Name: sa
  - Password:
