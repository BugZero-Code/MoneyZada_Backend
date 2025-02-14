package br.com.iuryalmeida.MoneyZada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iuryalmeida.MoneyZada.model.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}
