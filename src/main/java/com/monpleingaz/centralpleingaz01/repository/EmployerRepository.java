package com.monpleingaz.centralpleingaz01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monpleingaz.centralpleingaz01.model.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    Optional<Employer> findByUuidEmployer(String uiidEmployer);
}
