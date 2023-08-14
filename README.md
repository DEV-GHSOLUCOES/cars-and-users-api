# API RESTful para Sistema de Usuários de Carros

Este projeto implementa uma API RESTful para criação e gerenciamento de usuários e carros, com autenticação JWT.

## Estórias de Usuário para entidade User

História de Usuário 1 - Listagem de Usuarios:
Como um usuário, eu quero poder obter uma lista de usuarios disponíveis na API.

História de Usuário 2 - Cadastar um novo Usuarios
Como um usuário, eu quero poder cadastrar um novo usuario na API.

História de Usuário 3 - Buscar um usuário pelo id:
Como um usuário, eu quero poder buscar um usuário pelo id na API.

História de Usuário 4 - Remover um usuário pelo id:
Como um usuário, eu quero poder remover um usuário pelo id na API.

História de Usuário 5 - Atualizar um usuário pelo id:
Como um usuário, eu quero poder atualizar um usuário pelo id na API.

História de Usuário 6 - Autenticacao  do Usuario na api:
Como um usuário, eu quero poder me autenticar com usuario e senha na API.

## Estórias de Usuário para entidade Car

História de Usuário 1 - Listagem de carros:
Como um usuário, eu quero poder obter uma lista de carros disponíveis na API.

História de Usuário 2 - Cadastar um novo carro
Como um usuário, eu quero poder cadastrar um novo carro na API.

História de Usuário 3 - Buscar um carro pelo id:
Como um usuário, eu quero poder buscar um carro pelo id na API.

História de Usuário 4 - Remover um carro pelo id:
Como um usuário, eu quero poder remover um carro pelo id na API.

História de Usuário 5 - Atualizar um carro pelo id:
Como um usuário, eu quero poder atualizar um carro pelo id na API.


## Solução

Este projeto utiliza o framework Spring Boot para criar a API RESTful e o Spring Security com JWT para autenticação e autorização de usuários.

### Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database (em memória)
- JSON Web Token (JWT)
- Flay Way 

### Justificativas

- O uso do Spring Boot simplifica o desenvolvimento de aplicações Java, fornecendo configurações padrão e um ambiente de execução incorporado.

- O Spring Data JPA facilita a integração com o banco de dados e a implementação das operações de persistência.

- O Spring Security fornece recursos robustos de autenticação e autorização, e a autenticação JWT é usada para melhor segurança e escalabilidade.

- O banco de dados H2 em memória é utilizado para facilitar o desenvolvimento e os testes, mas pode ser substituído por outros bancos de dados em ambientes de produção.
- O uso do Flyway  foi para facilicar os testes durante o desenvolvimento, visto que o banco usado é o h2, para popular o banco teriamos que rodar os scripts manualmente para termos dados  de retorno na execucao dos  endpoints. Entao com o uso do flay way, configurei scripts de criacao e inserts para serem executados sempre que a aplicacao esta de pé. Alem de ser uma ferramenta que automatiza o processo de gerenciar e aplicar migrações de banco de dados. Ele permite controlar a evolução do esquema do banco, automatizando a execução de scripts SQL para criar, modificar ou reverter alterações. Isso garante um esquema consistente em diferentes ambientes, facilita a colaboração em equipe e mantém o controle das versões do banco de dados.

### Executando o Projeto

1. Clone o repositório: `git clone https://github.com/DEV-GHSOLUCOES/car-dealer-api.git`
2. Navegue para o diretório do projeto: `cd nome-do-projeto`
3. Compile o projeto: `mvn clean install`
4. Execute o projeto: `java -jar target/nome-do-arquivo.jar`

A API estará disponível em http://localhost:8080.

### Testes

Para executar os testes unitários, utilize o comando: `mvn test`


