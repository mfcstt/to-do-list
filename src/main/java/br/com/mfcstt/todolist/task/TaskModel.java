package br.com.mfcstt.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Anotação Lombok que gera automaticamente métodos getters, setters e outros métodos comuns.
@Entity(name = "tb_task") // Anotação JPA que indica que esta classe é uma entidade mapeada para uma tabela no banco de dados.

public class TaskModel {

    @Id // Anotação JPA que indica que o campo é uma chave primária.
    @GeneratedValue(generator = "UUID") // Gera automaticamente valores UUID para a chave primária.
    private UUID id; // Identificador único da tarefa.

    private String description; // Descrição da tarefa.

    @Column(length = 50) // Anotação JPA que limita o tamanho da coluna "title" a 50 caracteres.
    private String title; // Título da tarefa.

    private LocalDateTime startAt; // Data e hora de início da tarefa.

    private LocalDateTime endAt; // Data e hora de término da tarefa.

    private String priority; // Prioridade da tarefa.

    private UUID idUser; // Identificador do usuário ao qual a tarefa está associada.

    @CreationTimestamp // Anotação Hibernate que registra automaticamente a data e hora de criação.
    private LocalDateTime createdAt; // Data e hora em que a tarefa foi criada.

    // Método para definir o título da tarefa com validação de tamanho máximo.
    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("O título deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }
}
