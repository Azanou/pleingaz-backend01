package com.monpleingaz.centralpleingaz01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class RegionAgence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idRegion;

    @Column(unique = true)
   private String nomRegion;
}
