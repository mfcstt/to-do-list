package br.com.mfcstt.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController                   // Define que esta classe é um controlador Spring
@RequestMapping("/users")        // Define a estrutura da rota para as operações deste controlador

public class UserController {

    @Autowired
    private IUserRepository userRepository; // Injeção de dependência do repositório de usuários

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        // Manipula a criação de um novo usuário a partir dos dados no corpo da requisição

        // Verifica se um usuário com o mesmo nome de usuário já existe
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            // Se o usuário já existe, retorna uma resposta de erro com código HTTP 400 (BAD REQUEST)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        // Se o usuário não existe, realiza o hash da senha usando BCrypt
        var passwordHashed = BCrypt.withDefaults()
            .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashed); // Define a senha hash no modelo do usuário

        // Salva o novo usuário no banco de dados usando o repositório
        var userCreated = this.userRepository.save(userModel);

        // Retorna uma resposta de sucesso com o usuário criado e código HTTP 200 (OK)
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
