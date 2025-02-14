package br.com.iuryalmeida.MoneyZada.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "o nome nao pode ser nulo")
    private String name;

    @Email(message = "Email nao e valido")
    private String email;

    @Size(min = 6, message = "Password precisa ter no minimo 6 caracteres")
    private String password;
}