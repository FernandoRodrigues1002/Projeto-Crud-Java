# Projeto Crud em Java

## Fernando Rodrigues Del Franco

## Descrição

O objetivo do projeto é fazer um crud em java, utilizando spring boot, para gerenciar um sistema de cadastro de Pessoas e de Contatos, na qual uma pessoa pode ter nenhum ou vários contatos relacionados a mesma. No projeto o banco de dados utilizado foi o H2 o SpringBoot na verão 3.2.7. Os atributos das entidades Pessoa e Contato, podem ser inseridos manualmente, com exceção do ID

### Entidades

1. **Pessoa**: Representa uma pessoa genérica:
- ID (único, não pode ser nulo) 
- Matricula (único, não pode ser nulo)
- Nome (não pode ser nulo) 
- Endereço (pode ser nulo) 
- CEP (pode ser nulo) 
- Cidade (pode ser nulo) 
- UF (pode ser nulo) 

2. **Contato**: ID (único, não pode ser nulo) 
- Tipo contato (não pode ser nulo)  (0 Telefone, 1 Celular) 
- Contato (não pode ser nulo)
## Funcionalidades Pessoa

- Criar Pessoa => http://localhost:8080/api/pessoas (POST)

- Obter Pessoa por ID => http://localhost:8080/api/pessoas/{id} (GET)

- Obter Pessoa por ID para mala direta => http://localhost:8080/api/pessoas/maladireta/{id} (GET)

- Listar todas as Pessoas => http://localhost:8080/api/pessoas (GET)

- Atualizar Pessoa por ID  => http://localhost:8080/api/pessoas/{id} (PUT)

- Deletar Pessoa por ID  => http://localhost:8080/api/pessoas/{id} (DELETE)

## Funcionalidades Contato

- Adicionar um novo Contato a uma Pessoa => http://localhost:8080/api/contatos/{idPessoa} (POST)

- Obter Contato por ID => http://localhost:8080/api/contatos/{id} (GET)

- Listar todos os Contatos de uma Pessoa => http://localhost:8080/api/contatos/pessoas/{idPessoa} (GET)

- Atualizar Contato por ID => http://localhost:8080/api/contatos/{id} (PUT)

- Deletar Contato por ID => http://localhost:8080/api/contatos/{id} (DELETE) 

