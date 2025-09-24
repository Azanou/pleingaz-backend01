package com.monpleingaz.centralpleingaz01.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monpleingaz.centralpleingaz01.dto.AgenceClassementDTO;
import com.monpleingaz.centralpleingaz01.dto.AgenceDetailsDTO;
import com.monpleingaz.centralpleingaz01.dto.DayResumeDTO;
import com.monpleingaz.centralpleingaz01.dto.VendeurArticleVenduDTO;
import com.monpleingaz.centralpleingaz01.dto.VendeurClassementDTO;
import com.monpleingaz.centralpleingaz01.dto.VendeurDetailsDTO;

import com.monpleingaz.centralpleingaz01.repository.VenteRepository;


@Service
public class CommercialService {

    @Autowired
    private VenteRepository venteRepository;


    public DayResumeDTO getDayResume(LocalDate date) {

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        return venteRepository.getDayResume(startOfDay, endOfDay);
    }

    public List<DayResumeDTO> getDailyResumes(LocalDate starDate, LocalDate endDate) {
        LocalDateTime start = starDate.atStartOfDay();
        LocalDateTime end = endDate.plusDays(1).atStartOfDay();

        return venteRepository.getDayResumeForPeriod(start, end);
    }

    public List<VendeurDetailsDTO> getDailyVendeurDetails(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        List<VendeurClassementDTO> classement = venteRepository.getClassementVendeursJournalier(startOfDay, endOfDay);

        return classement.stream()
                .map(vendeurClassement -> {
                    List<VendeurArticleVenduDTO> articlesVendus = venteRepository.getArticlesVendusParVendeur(
                            startOfDay, endOfDay, vendeurClassement.getNomVendeur());
                    return new VendeurDetailsDTO(vendeurClassement.getNomVendeur(), vendeurClassement.getTotalVentes(),
                            articlesVendus);
                })
                .collect(Collectors.toList());
    }

    // ... (reste du code)

    public List<AgenceDetailsDTO> getDailyAgenceDetails(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        List<AgenceClassementDTO> classement = venteRepository.getClassementAgencesJournalier(startOfDay, endOfDay);

        return classement.stream()
                .map(agenceClassement -> {
                    List<VendeurArticleVenduDTO> articlesVendus = venteRepository.getArticlesVendusParAgence(
                            startOfDay, endOfDay, agenceClassement.getNomAgence());
                    return new AgenceDetailsDTO(agenceClassement.getNomAgence(), agenceClassement.getTotalVentes(),
                            articlesVendus);
                })
                .collect(Collectors.toList());
    }

    public List<VendeurArticleVenduDTO> getDailyClassementArticles(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        return venteRepository.getClassementArticlesJournalier(startOfDay, endOfDay);
    }

    // // Action CRUD sur ces donnees :
    // //------------------------------------------------------------
    // // 1. creation d'un vendeur
    // public Vendeur createVendeur(VendeurCreationRequestDTO requestDTO){
    //     try{
    //         Vendeur vendeur = new Vendeur();
    //         vendeur.setNomVendeur(requestDTO.getNomVendeur());
    //         vendeur.setEmailVendeur(requestDTO.getEmailVendeur());
    //         vendeur.setNumeroDetelephoneVendeur(requestDTO.getEmailVendeur());
    //         return vendeurRepository.save(vendeur);

    //     }catch(DataIntegrityViolationException e){
    //         throw new IllegalArgumentException("Un vendeur avec ce nom existe déjà.");
    //     }   
    // }

    // //2

}
