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
- Junit
- Swagger
- RAILWAY 
### Justificativas

- O uso do Spring Boot simplifica o desenvolvimento de aplicações Java, fornecendo configurações padrão e um ambiente de execução incorporado.

- O Spring Data JPA facilita a integração com o banco de dados e a implementação das operações de persistência.

- O Spring Security fornece recursos robustos de autenticação e autorização, e a autenticação JWT é usada para melhor segurança e escalabilidade.

- O banco de dados H2 em memória é utilizado para facilitar o desenvolvimento e os testes, mas pode ser substituído por outros bancos de dados em ambientes de produção.
  
- O JUnit é um framework de testes unitários amplamente utilizado na comunidade de desenvolvimento de software. Ele fornece uma estrutura para escrever, organizar e executar testes automatizados em código Java.

- O Swagger é uma estrutura (framework) de software que permite projetar, criar, documentar e consumir APIs de forma mais eficiente. Ele simplifica o processo de desenvolvimento de APIs RESTful ao fornecer uma maneira padronizada de descrever a interface da API, permitindo que desenvolvedores e equipes colaborem de maneira mais eficaz
  
- O Railway é uma plataforma semelhante ao heroku. A escolha dele foi pelo motivo do heroku nao ser mais gratuito, houve problemas com a AWS.

### Para testar localmente a API,  bastar  ter uma ferramenta como postman, e realizar as requisicoes para as URIs, para as URIs que precisma de autenticacao, é preciso inserir o token no header  

###  A API estará disponível: https://cars-and-users-api-production.up.railway.app/cars-and-users-api/api/users

### Sawgger : https://cars-and-users-api-production.up.railway.app/cars-and-users-api/swagger-ui/index.html#/car-controller



