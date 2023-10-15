


# Conteúdo

- [Projeto](#projeto)
    - [Aplicação](#Aplicação)
    - [Links](#Links)
- [Tecnologias Utilizadas](#Tecnologias-Utilizadas)
- [Recursos úteis](#Recursos-Úteis)
- [Autor](#Autor)



## 💻 Projeto

API To-Do List desenvolvida em Java 17 e Spring Boot. Ele usa o API Dog para testar as funcionalidades e o Maven para gerenciamento de dependências. O banco de dados utilizado é o H2.


### ☁️ Aplicação

Clone este repositório e execute o seguinte comando: `mvn spring-boot:run`.

Para acessar suas funcionalidades recomendo utilizar o API Dog ou Rest Clients similares 

##### 🖱 Endpoints
###### Cadastrar usuário -> POST - https://todolist-rocket-x1be.onrender.com/user
```json


{
"name": "Maria Fernanda",
"username":"mfcstt",
"password":"01234"
}
```

###### Cadastrar tarefa -> POST - https://todolist-rocket-x1be.onrender.com/tasks/
- Para criar uma tarefa é necessário utilizar a autenticação de um usuário existente
- Username e senha cadastrados
```json

{

  "description":"Realizar curso Java da Rocketseat",
  "title":"SpringBoot",
  "priority": "High",
  "startAt":"2023-10-30T08:30:00",
  "endAt":"2023-10-30T11:30:00"

}
```

###### Pesquisar tarefas -> GET - https://todolist-rocket-x1be.onrender.com/tasks/
- Para recuperar uma tarefa é necessário utilizar a autenticação de um usuário existente
- Username e senha cadastrados

###### Atualizar tarefas -> PUT - https://todolist-rocket-x1be.onrender.com/tasks/UUID-da-tarefa
- Para atualizar uma tarefa é necessário inserir o ID da tarefa desejada e também utilizar a autenticação de um usuário existente
- Username e senha cadastrados
```json

{

  "title": "Curso Java",
  
}
```

## 🔗 Links
- URL do deploy: [ToDoList](https://todolist-rocket-x1be.onrender.com)


## 🖤 Tecnologias utilizadas
- Java 17
- Maven
- Spring Boot
- Spring Initializr 
- Spring Data JPA
- spring-boot-devtools 
- Banco de Dados H2
- Project Lombok 
- BCrypt
- Render  


## 📑 Recursos úteis
-  [Spring initializer](https://start.spring.io)
- [Documentação Spring-Boot](https://spring.io/guides/gs/spring-boot/)


## 💬 Autor
- GitHub- [mfcstt](https://github.com/mfcstt)
- LinkedIn - [Maria Fernanda](https://www.linkedin.com/in/mfcstt)



