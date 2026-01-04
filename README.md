
# Technician Companion

Uma API que tem o objetivo de padronizar relatórios de atendimentos em campo.

## Stack utilizada


**Back-end:** Java Spring Boot.


## Requisitos

- [Java JDK 21]
- [Apache Maven 3.9.11]
- [Git 2.51.2]

Verifique as versões com: 
```bash 
java -version 
mvn -v 
git --version
```
## Instalação

Instale Technician Companion com Git clone

```bash
  git clone https://github.com/Neyzim/TechnicianCompanion - Para clonar este Repositorio.
  cd seurepositorio/TechnicianCompanion - Para entrar na pasta do projeto.
  mvn clean install - Para instalar as dependências.
  mvn spring-boot:run - Para rodar a aplicação.
```
    
## Documentação da API

Este projeto utiliza **Swagger/OpenAPI** para documentar os endpoints. 
Após iniciar a aplicação, acesse: 
- Interface Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 
- Especificação OpenAPI (JSON): [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) 

Com isso você poderá visualizar, testar e explorar todos os endpoints da API diretamente pelo navegador.