package br.com.iuryalmeida.MoneyZada.repository;

import br.com.iuryalmeida.MoneyZada.model.Expense;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
