package br.com.iuryalmeida.MoneyZada.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.iuryalmeida.MoneyZada.model.User;
import br.com.iuryalmeida.MoneyZada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
@Tag(name = "Usuário", description = "Endpoints para gerenciamento de usuários")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Operation(summary = "Registrar um novo usuário", description = "Salva um novo usuário no banco de dados")
    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado com sucesso");
    }
}