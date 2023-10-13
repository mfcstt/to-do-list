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


// configurar rota
@RestController
@RequestMapping("/tasks")
public class TaskController {

    //autogerenciar repositorio
    @Autowired
    private ITaskRepository taskRepository;

    //rota post
    //criar tarefa
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID)idUser);

        //validação da data
        var currentDate =LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A data de inicio/termino deve ser maior do que a data atual");
        }
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A data de inicio deve ser menor do que a data de termino");
        }
        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    //lista de tarefas
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
        
    }
    
    //atualizar tarefa
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel,HttpServletRequest request, @PathVariable UUID id ){
        
        var task = this.taskRepository.findById(id).orElse(null);

        // verificar se a tarefa existe
        if(task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Tarefa não encontrada");

        }
        
        //verificar se a tarefa pertence ao usuário

        var idUser = request.getAttribute("idUser");
        if(!task.getIdUser().equals(idUser)){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Permissão não concedida");   
        }

        utils.copyNonNullProperties(taskModel, task);
        
        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdated);
    }
    
}
