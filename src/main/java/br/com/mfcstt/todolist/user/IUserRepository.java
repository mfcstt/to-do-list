package br.com.mfcstt.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    // Método para encontrar um usuário pelo seu nome de usuário (username)
    UserModel findByUsername(String username);
}

