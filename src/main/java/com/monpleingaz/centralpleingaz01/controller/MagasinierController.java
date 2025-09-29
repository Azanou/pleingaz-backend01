package com.monpleingaz.centralpleingaz01.controller;

import com.monpleingaz.centralpleingaz01.service.MagasinierService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/magasinier")
public class MagasinierController {

    private final MagasinierService MagasinierService;

    public MagasinierController(MagasinierService MagasinierService) {
        this.MagasinierService = MagasinierService;
    }

    @PreAuthorize("hasRole('MAGASINIER')")
    @PostMapping("/entre")
    public ResponseEntity<String> enregistrerEntreeStock(@RequestBody Map<String, Integer> entrerStock, Authentication authentication) {
        
        // 1. Récupération du JWT (le token)
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Map<String, Object> claims = jwt.getClaims();

        // 2. Passage des claims et des données au service
        try {
            MagasinierService.enregistrerEntree(entrerStock, claims);
            return ResponseEntity.ok("Entrée de stock enregistrée avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'enregistrement de l'entrée de stock: " + e.getMessage());
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }
}