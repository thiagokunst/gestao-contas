
### Sobre

Este projeto consiste em uma API de Gestão de Contas\
A documentação se encontra em http://localhost:8080/ \
Uma collection no Postman também foi disponibilizada para facilitar os testes, para utilizá-la basta importar o `Gestão de Contas.postman_collection.json` em seu Postman \
São utilizados dois containers Docker, um para a aplicação e outro para o SQL Server 2022

---
### Tecnologias Utilizadas

- JDK 17
- Maven 4.0
- Spring Boot 2.7.5
- SQL Server 2022
- Swagger
- Flyway
- Lombok
- Docker

---

### Instruções para montar o ambiente de desenvolvimento

1. Certifique-se de que o Maven e o Docker estão instalados em sua máquina.
2. `docker-compose build` (para buildar as imagens contidas no docker-compose.yml)
3. `docker-compose up` (para criar e executar os containers)
4. Acesse http://localhost:8080/ para consultar a documentação ou a collection do Postman

---

### Instruções de utilização da API

Uma pessoa com o CPF 39477766642 foi cadastrada na tabela pessoas.\
Utilize esse CPF para criar uma conta\
No retorno da criação da conta, você receberá o idConta, utlize esse valor para fazer outras operações como depósito, saque, consulta de extrato.

