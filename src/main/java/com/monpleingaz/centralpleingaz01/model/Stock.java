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
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //recemment rajouter
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_agence")
    private Agence agence;

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    private Integer quantite;
}
