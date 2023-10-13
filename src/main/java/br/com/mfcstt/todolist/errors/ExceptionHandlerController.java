package br.com.mfcstt.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Anotação que define esta classe como um controlador de exceções global.
public class ExceptionHandlerController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableExcption(HttpMessageNotReadableException e) {
        // Método para lidar com exceções do tipo HttpMessageNotReadableException.

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
        // Retorna uma resposta de erro HTTP 400 (BAD_REQUEST) com a mensagem da exceção.
    }
}
