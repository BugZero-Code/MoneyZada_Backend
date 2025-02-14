package br.com.iuryalmeida.MoneyZada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iuryalmeida.MoneyZada.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}