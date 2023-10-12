package br.com.mfcstt.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    //encontrar pelo username para fazer validação
    UserModel findByUsername(String username);
    
}
