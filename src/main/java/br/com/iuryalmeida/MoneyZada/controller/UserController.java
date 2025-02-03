package br.com.iuryalmeida.MoneyZada.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.iuryalmeida.MoneyZada.user.User;

@RestController
@RequestMapping("/register")
public class UserController {

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Aqui você pode adicionar a lógica para salvar o usuário no banco de dados
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}


//vamos testar