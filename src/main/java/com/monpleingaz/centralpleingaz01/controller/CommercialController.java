package com.monpleingaz.centralpleingaz01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monpleingaz.centralpleingaz01.dto.AgenceDetailsDTO;
import com.monpleingaz.centralpleingaz01.dto.DayResumeDTO;
import com.monpleingaz.centralpleingaz01.dto.VendeurArticleVenduDTO;
import com.monpleingaz.centralpleingaz01.dto.VendeurDetailsDTO;
import com.monpleingaz.centralpleingaz01.service.CommercialService;


import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/commercial")
public class CommercialController {

    private final CommercialService commercialService;

    public CommercialController(CommercialService commercialService){
        this.commercialService = commercialService;
    }
    
    //liste le infos globales journalieres sur les ventes
    @GetMapping("/daily/{date}")
    public DayResumeDTO getDailyResume(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return commercialService.getDayResume(localDate);
    }

    //retourne les details des ventes sur une periode donnee
    @GetMapping("/daily-resumes")
    public List<DayResumeDTO> getDailyResumes(@RequestParam String startDate, @RequestParam String endDate){
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate); 
        return commercialService.getDailyResumes(start, end);
    }

    //Classement journalier des meilleurs vendeurs
    @GetMapping("/daily-classement-vendeurs/{date}")
    public List<VendeurDetailsDTO> getDailyClassementVendeurs(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return commercialService.getDailyVendeurDetails(localDate);
    }

    //classement journalier des meilleurs agences 
    @GetMapping("/daily-classement-agences/{date}")
    public List<AgenceDetailsDTO> getDailyClassementAgences(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return commercialService.getDailyAgenceDetails(localDate);
    }

    //classement des meilleurs articles de la journee (les plus vendus)
    @GetMapping("/daily-classement-articles/{date}")
    public List<VendeurArticleVenduDTO> getDailyClassementArticles(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return commercialService.getDailyClassementArticles(localDate);
    }

    //II. Actions CRUD  du commercial 

    // 1. creation de vendeur
    // @PostMapping
    // public ResponseEntity<Vendeur> createVendeur(@RequestBody VendeurCreationRequestDTO requestDTO) {
    //     try {
    //         Vendeur newVendeur = commercialService.createVendeur(requestDTO);
    //         return new ResponseEntity<>(newVendeur,HttpStatus.CREATED);
    //     } catch (IllegalArgumentException e) {
    //         return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409 Conflict
    //     }
    // }
}
