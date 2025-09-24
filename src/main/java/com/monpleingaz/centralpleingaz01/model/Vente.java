package com.monpleingaz.centralpleingaz01.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVente;

    private LocalDateTime dateVente;

    @ManyToOne
    @JoinColumn(name = "region")
    private RegionAgence region;

    @ManyToOne
    @JoinColumn(name = "agence")
    private Agence agence;

    @ManyToOne
    @JoinColumn(name = "vehicule", nullable = true)
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name= "chauffeur", nullable = true)
    // @Column(nullable = true)
    private Employer chauffeur;

    @ManyToOne
    @JoinColumn(name= "manutentionnaire", nullable = true)
    // @Column(nullable = true)
    private Employer manutentionnaire;

    @ManyToOne
    @JoinColumn(name = "numFacture", nullable = true)
    // @Column(nullable = true)
    private Facture numFacture;

    @ManyToOne
    @JoinColumn(name = "client", nullable = true)
    // @Column(nullable = true)
    private Client client;

   
    private String detail;

    @ManyToOne
    @JoinColumn(name = "id_vendeur", nullable = true)
    // @Column(nullable = true)
    private Employer vendeur;

    @ManyToOne
    @JoinColumn(name = "article", nullable = true)
    // @Column(nullable = true)
    private Article article;

    private Integer quantite;
    private Double tonneMetrique;

    //@ManyToOne
    //@JoinColumn(name = "prixUnitaire") #on y reviendras apres 
    private Double prixUnitaire ;

    private Double netCommercial;
    private Double remise;

    private Double netAPayer; //montant attendu
    private Double payerComptant;//1

    private Double creance; //2
    private Double recouvrement; //3

    private Double avanceCommande; //4
    private Double recouvrementCyl; 

    private String observation;

}
