package com.monpleingaz.centralpleingaz01.model;

import jakarta.persistence.CascadeType;
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
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmployer;

    private String nomEmployer;

    @Column(unique=true)
    private String uuidEmployer;                                     
    private String emailEmployer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_agence")
    private Agence Agence;

    private String numeroEmployer;

}
