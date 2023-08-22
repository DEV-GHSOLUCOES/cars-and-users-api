# API RESTful para Sistema de Usuários de Carros

Este projeto implementa uma API RESTful para criação e gerenciamento de usuários e carros, com autenticação JWT.

## Estórias de Usuário

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
Como um usuário, eu quero poder me autenticar com login e password na API e devo ter como retorno um  token
de acesso da API (JWT) com as informações do usuário logado.

Historia de Usuario 7 - Listagem dos carros:
Como usuario , eu quero poder obter  uma lista de todos os carros do usuario logado

Historia de Usuario 8 -  Cadastrar um novo carro para o usuario logado:
Como Usuario, eu quero poder cadastrar um novo carro para o usuario logado.

Historia de Usuario 9 -  Buscar um carro do usuario logado pelo id:
Como Usuario, eu quero poder buscar um carro do usuario logado pelo id.

Historia de Usuario 10 - Remover um carro do usuário logado pelo id:
Como Usuario, eu quero poder remover um carro do usuario logado pelo id.

Historia de Usuario 11 - Atualizar um carro do usuário logado pelo id:
Como Usuario, eu quero poder atualizar um carro do usuario logado pelo id.

Historia de Usuario 12 - Retornar as informações do usuário logado:
Como Usuario, eu quero retornar as informações do usuário logado (firstName, lastName, email, birthday, login,
phone, cars) + createdAt (data da criação do usuário) + lastLogin (data da última vez
que o usuário realizou login).


## Solução

Este projeto utiliza o framework Spring Boot para criar a API RESTful e o Spring Security com JWT para autenticação e autorização de usuários.

### Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database (em memória)
- JSON Web Token (JWT)

### Justificativas

- O uso do Spring Boot simplifica o desenvolvimento de aplicações Java, fornecendo configurações padrão e um ambiente de execução incorporado.

- O Spring Data JPA facilita a integração com o banco de dados e a implementação das operações de persistência.

- O Spring Security fornece recursos robustos de autenticação e autorização, e a autenticação JWT é usada para melhor segurança e escalabilidade.

- O banco de dados H2 em memória é utilizado para facilitar o desenvolvimento e os testes, mas pode ser substituído por outros bancos de dados em ambientes de produção.

### Executando o Projeto

1. Clone o repositório: `git clone https://github.com/DEV-GHSOLUCOES/car-dealer-api.git`
2. Navegue para o diretório do projeto: `cd nome-do-projeto`
3. Compile o projeto: `mvn clean install`
4. Execute o projeto: `java -jar target/nome-do-arquivo.jar`

A API estará disponível em http://localhost:8080.

### Testes

Para executar os testes unitários, utilize o comando: `mvn test`


