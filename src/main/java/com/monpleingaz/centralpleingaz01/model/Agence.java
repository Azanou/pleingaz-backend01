package com.monpleingaz.centralpleingaz01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAgence;

    @ManyToOne
    @JoinColumn(name = "region")
    private RegionAgence region;

    @Column(unique = true)
    private String nomAgence;
}
