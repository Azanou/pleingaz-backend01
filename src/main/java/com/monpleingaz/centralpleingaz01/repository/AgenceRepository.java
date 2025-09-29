package com.monpleingaz.centralpleingaz01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monpleingaz.centralpleingaz01.model.Agence;

public interface AgenceRepository extends JpaRepository<Agence, Integer> {
    Optional<Agence> findByNomAgence(String nomAgence);
}
