package com.monpleingaz.centralpleingaz01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monpleingaz.centralpleingaz01.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

}
