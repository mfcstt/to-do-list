package br.com.mfcstt.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Anotação Lombok que gera automaticamente métodos getters, setters e outros métodos comuns.
@Entity(name="tb_users") // Anotação JPA que indica que esta classe é uma entidade mapeada para uma tabela no banco de dados.

public class UserModel {

    @Id // Anotação JPA que indica que o campo é uma chave primária.
    @GeneratedValue(generator = "UUID") // Gera automaticamente valores UUID para a chave primária.
    private UUID id; // Identificador único do usuário.

    @Column(unique = true) // Anotação JPA que indica que este campo deve ser único na tabela.
    private String username; // Nome de usuário do usuário.

    private String name; // Nome do usuário.

    private String password; // Senha do usuário.

    @CreationTimestamp // Anotação Hibernate que registra automaticamente a data e hora de criação.
    private LocalDateTime createdAt; // Data e hora em que o usuário foi criado.
}
