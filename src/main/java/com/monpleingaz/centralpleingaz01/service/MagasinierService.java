package com.monpleingaz.centralpleingaz01.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monpleingaz.centralpleingaz01.model.Agence;
import com.monpleingaz.centralpleingaz01.model.Article;
import com.monpleingaz.centralpleingaz01.model.Stock;
import com.monpleingaz.centralpleingaz01.repository.AgenceRepository;
import com.monpleingaz.centralpleingaz01.repository.ArticleRepository;
import com.monpleingaz.centralpleingaz01.repository.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class MagasinierService {
    

    

    private final StockRepository stockRepository;
    private final ArticleRepository articleRepository;
    private final AgenceRepository agenceRepository;

    public MagasinierService(StockRepository stockRepository, ArticleRepository articleRepository, AgenceRepository agenceRepository) {
        this.stockRepository = stockRepository;
        this.articleRepository = articleRepository;
        this.agenceRepository = agenceRepository;
    }

    @Transactional
    public void enregistrerEntree(Map<String, Integer> entrerStock, Map<String, Object> claims) {
        
        // 1. Récupérer le nom de l'agence depuis les claims du token
        String nomAgence = (String) claims.get("agence"); // **<-- ATTENTION : Remplacer "agence_claim_name" par le nom de la propriété de votre claim.**
        if (nomAgence == null) {
            throw new RuntimeException("Le token ne contient pas la claim de l'agence.");
        }
        
        // 2. Chercher l'agence correspondante dans la BDD
        Optional<Agence> optionalAgence = agenceRepository.findByNomAgence(nomAgence);
        if (optionalAgence.isEmpty()) {
            throw new RuntimeException("Agence '" + nomAgence + "' introuvable dans la base de données.");
        }
        Agence agence = optionalAgence.get();
        
        // 3. Boucler sur les données du formulaire
        for (Map.Entry<String, Integer> entree : entrerStock.entrySet()) {
            String nomArticle = entree.getKey();
            Integer quantite = entree.getValue();
            
            // Chercher l'article par son nom
            Article article = articleRepository.findByNomArticle(nomArticle);
            
            Stock nouveauStock = new Stock();
            nouveauStock.setDate(LocalDateTime.now());
            nouveauStock.setAgence(agence);
            nouveauStock.setArticle(article);
            nouveauStock.setQuantite(quantite);
            
            stockRepository.save(nouveauStock);
            
        }
    }
}
