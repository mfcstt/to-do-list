package br.com.mfcstt.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    // Método para encontrar tarefas por ID de usuário
    List<TaskModel> findByIdUser(UUID idUser);
}
