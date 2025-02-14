package br.com.iuryalmeida.MoneyZada.controller;

import br.com.iuryalmeida.MoneyZada.model.Revenue;
import br.com.iuryalmeida.MoneyZada.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/revenues")
public class RevenueController {

    @Autowired
    private RevenueRepository revenueRepository;

    @PostMapping
    public ResponseEntity<String> registerRevenue(@Valid @RequestBody Revenue revenue) {
        revenueRepository.save(revenue);
        return new ResponseEntity<>("Receita registrada com sucesso", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllRevenues() {
        return ResponseEntity.ok(revenueRepository.findAll());
    }}
