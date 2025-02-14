package br.com.iuryalmeida.MoneyZada.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.iuryalmeida.MoneyZada.model.Expense;
import br.com.iuryalmeida.MoneyZada.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/expenses")
@Tag(name = "Despesas", description = "Endpoints para gerenciamento de despesas")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Operation(summary = "Registrar uma nova despesa", description = "Salva uma nova despesa no banco de dados")
    @PostMapping
    public ResponseEntity<String> registerExpense(@Valid @RequestBody Expense expense) {
        expenseRepository.save(expense);
        return new ResponseEntity<>("Despesa registrada com sucesso", HttpStatus.CREATED);
    }
    

    @Operation(summary = "Listar todas as despesas", description = "Retorna uma lista com todas as despesas registradas")
    @GetMapping
    public ResponseEntity<?> getAllExpenses() {
        return ResponseEntity.ok(expenseRepository.findAll());
    }

    @Operation(summary = "Buscar uma despesa por ID", description = "Retorna os dados de uma despesa específica pelo seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable Long id) {
        return expenseRepository.findById(id)
                .map(expense -> ResponseEntity.ok(expense))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

@Operation(summary = "Editar uma despesa existente", description = "Atualiza os dados de uma despesa pelo seu ID")
@PatchMapping("/{id}")
public ResponseEntity<String> updateExpense(@PathVariable Long id, @Valid @RequestBody Expense updatedExpense) {
    return expenseRepository.findById(id)
            .map(existingExpense -> {
                existingExpense.setName(updatedExpense.getName());
                existingExpense.setAmount(updatedExpense.getAmount());
                expenseRepository.save(existingExpense);
                return new ResponseEntity<>("Despesa atualizada com sucesso", HttpStatus.OK);
            })
            .orElseGet(() -> new ResponseEntity<>("Despesa não encontrada", HttpStatus.NOT_FOUND));
}
}