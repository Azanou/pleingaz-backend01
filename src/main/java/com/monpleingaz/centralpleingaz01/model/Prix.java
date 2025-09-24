package com.monpleingaz.centralpleingaz01.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Prix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrix;

    @ManyToOne
    @JoinColumn(name="article" , nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name="client", nullable = false)
    private Client client;

    private Double prixToutClient;
    
    private Double prixCentreEnfuteur;

}
