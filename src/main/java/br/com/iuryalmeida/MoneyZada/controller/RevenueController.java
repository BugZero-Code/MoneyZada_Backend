package br.com.iuryalmeida.MoneyZada.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.iuryalmeida.MoneyZada.model.Revenue;
import br.com.iuryalmeida.MoneyZada.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/revenues")
@Tag(name = "Receitas", description = "Endpoints para gerenciamento de receitas")
public class RevenueController {

    @Autowired
    private RevenueRepository revenueRepository;

    @Operation(summary = "Registrar uma nova receita", description = "Salva uma nova receita no banco de dados")
    @PostMapping
    public ResponseEntity<String> registerRevenue(@Valid @RequestBody Revenue revenue) {
        revenueRepository.save(revenue);
        return new ResponseEntity<>("Receita registrada com sucesso", HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas as receitas", description = "Retorna uma lista com todas as receitas registradas")
    @GetMapping
    public ResponseEntity<?> getAllRevenues() {
        return ResponseEntity.ok(revenueRepository.findAll());
    }

    @Operation(summary = "Buscar uma receita por ID", description = "Retorna os dados de uma receita específica pelo seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<Revenue> getRevenue(@PathVariable Long id) {
        return revenueRepository.findById(id)
                .map(revenue -> ResponseEntity.ok(revenue))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
