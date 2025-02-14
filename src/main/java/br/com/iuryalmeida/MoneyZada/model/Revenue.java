package br.com.iuryalmeida.MoneyZada.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Revenue {
    @Id
    private Long id;
    private String description;
    private Double amount;
    private String date;

    // Getters e Setters
}
