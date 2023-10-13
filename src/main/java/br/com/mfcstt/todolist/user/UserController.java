package br.com.mfcstt.todolist.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController                   // criação da rota
@RequestMapping("/users")        // estrutura da rota

public class UserController {

    //Gerenciamento do metodo Repository 
    //Chamar a interface
    @Autowired
    private IUserRepository userRepository;

    
// Metodo da classe de cadastro
//Body da requisição

@PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        //encontrar pelo username e validar caso o usuario ja existir
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            //mensagem de erro
            //status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        //criptografar a senha
       var passwordHashred = BCrypt.withDefaults()
       .hashToString(12,userModel.getPassword().toCharArray());
        //passar a senha
        userModel.setPassword(passwordHashred);

        //retornar quando o usuario for cadastrado
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
