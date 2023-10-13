package br.com.mfcstt.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mfcstt.todolist.utils.utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController                   // Define que esta classe é um controlador Spring
@RequestMapping("/tasks")        // Define a estrutura da rota para as operações deste controlador

public class TaskController {

    @Autowired
    private ITaskRepository taskRepository; // Injeção de dependência do repositório de tarefas

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        // Manipula a criação de uma nova tarefa a partir dos dados no corpo da requisição

        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser); // Define o ID do usuário para a tarefa

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            // Verifica se a data de início ou término da tarefa é anterior à data atual
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("A data de início/termino deve ser maior do que a data atual");
        }
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            // Verifica se a data de início é posterior à data de término
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("A data de início deve ser menor do que a data de término");
        }
        
        var task = this.taskRepository.save(taskModel); // Salva a nova tarefa no banco de dados
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        // Lista as tarefas associadas ao usuário com base no ID do usuário

        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser); // Busca as tarefas do usuário
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
        // Manipula a atualização de uma tarefa existente

        var task = this.taskRepository.findById(id).orElse(null); // Busca a tarefa pelo ID

        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Tarefa não encontrada");
        }

        var idUser = request.getAttribute("idUser");
        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Permissão não concedida");
        }

        utils.copyNonNullProperties(taskModel, task); // Atualiza as propriedades não nulas da tarefa existente

        var taskUpdated = this.taskRepository.save(task); // Salva a tarefa atualizada no banco de dados
        return ResponseEntity.ok().body(taskUpdated);
    }
}
