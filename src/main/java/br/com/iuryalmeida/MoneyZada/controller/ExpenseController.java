package br.com.iuryalmeida.MoneyZada.controller;

import br.com.iuryalmeida.MoneyZada.model.Expense;
import br.com.iuryalmeida.MoneyZada.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping
    public ResponseEntity<String> registerExpense(@Valid @RequestBody Expense expense) {
        expenseRepository.save(expense);
        return new ResponseEntity<>("Despesa registrada com sucesso", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllExpenses() {
        return ResponseEntity.ok(expenseRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable Long id) {
        return expenseRepository.findById(id)
                .map(expense -> ResponseEntity.ok(expense))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
