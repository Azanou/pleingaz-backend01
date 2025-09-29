package com.monpleingaz.centralpleingaz01.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.monpleingaz.centralpleingaz01.dto.AgenceClassementDTO;
import com.monpleingaz.centralpleingaz01.dto.DayResumeDTO;
import com.monpleingaz.centralpleingaz01.dto.VendeurArticleVenduDTO;
import com.monpleingaz.centralpleingaz01.dto.VendeurClassementDTO;
import com.monpleingaz.centralpleingaz01.model.Vente;

public interface VenteRepository extends JpaRepository<Vente, Integer> {

    // Requête pour un résumé sur une seule journée
   @Query("SELECT new com.monpleingaz.centralpleingaz01.dto.DayResumeDTO(" +
       "CAST(v.dateVente AS LocalDate), " +
       "SUM(v.netAPayer), " +
       "SUM(v.payerComptant), " +
       "SUM(v.creance), " +
       "SUM(v.recouvrement), " +
       "SUM(v.avanceCommande)) " +
       "FROM Vente v " +
       "WHERE v.dateVente >= :startOfDay AND v.dateVente < :endOfDay " +
       "GROUP BY CAST(v.dateVente AS LocalDate) " +
       "ORDER BY CAST(v.dateVente AS LocalDate)")
    DayResumeDTO getDayResume(LocalDateTime startOfDay, LocalDateTime endOfDay);


    // Requête pour une liste de résumés sur une période
   
    @Query("SELECT new com.monpleingaz.centralpleingaz01.dto.DayResumeDTO(" +
       "CAST(v.dateVente AS LocalDate), " +
       "SUM(v.netAPayer), " +
       "SUM(v.payerComptant), " +
       "SUM(v.creance), " +
       "SUM(v.recouvrement), " +
       "SUM(v.avanceCommande)) " +
       "FROM Vente v " +
       "WHERE v.dateVente >= :startOfDay AND v.dateVente < :endOfDay " +
       "GROUP BY CAST(v.dateVente AS LocalDate) " +
       "ORDER BY CAST(v.dateVente AS LocalDate)")
    List<DayResumeDTO> getDayResumeForPeriod(LocalDateTime startOfDay, LocalDateTime endOfDay);

    // liste des nomvendeur + montant vendu la journee
    @Query("SELECT new com.monpleingaz.centralpleingaz01.dto.VendeurClassementDTO(" +
            "v.vendeur.nomEmployer, SUM(v.netAPayer)) " +
            "FROM Vente v WHERE v.dateVente >= :startOfDay AND v.dateVente < :endOfDay " +
            "GROUP BY v.vendeur.nomEmployer " +
            "ORDER BY SUM(v.netAPayer) DESC")
    List<VendeurClassementDTO> getClassementVendeursJournalier(LocalDateTime startOfDay, LocalDateTime endOfDay);

    // liste des articles vendus avec quantite + tonneMetrique
    @Query("SELECT new com.monpleingaz.centralpleingaz01.dto.VendeurArticleVenduDTO(" +
            "v.article.nomArticle, SUM(v.quantite), SUM(v.tonneMetrique)) " +
            "FROM Vente v WHERE v.dateVente >= :startOfDay AND v.dateVente < :endOfDay AND v.vendeur.nomEmployer = :nomEmployer "
            +
            "GROUP BY v.article.nomArticle")
    List<VendeurArticleVenduDTO> getArticlesVendusParVendeur(LocalDateTime startOfDay, LocalDateTime endOfDay,
            String nomEmployer);

    // Ici , on a les meilleures agences par jour
    @Query("SELECT new com.monpleingaz.centralpleingaz01.dto.AgenceClassementDTO(" +
            "v.agence.nomAgence, SUM(v.netAPayer)) " +
            "FROM Vente v WHERE v.dateVente >= :startOfDay AND v.dateVente < :endOfDay " +
            "GROUP BY v.agence.nomAgence " +
            "ORDER BY SUM(v.netAPayer) DESC")
    List<AgenceClassementDTO> getClassementAgencesJournalier(LocalDateTime startOfDay, LocalDateTime endOfDay);

    // articles vendus a l'agence le jour
    @Query("SELECT new com.monpleingaz.centralpleingaz01.dto.VendeurArticleVenduDTO(" +
            "v.article.nomArticle, SUM(v.quantite), SUM(v.tonneMetrique)) " +
            "FROM Vente v WHERE v.dateVente >= :startOfDay AND v.dateVente < :endOfDay AND v.agence.nomAgence = :nomAgence "
            +
            "GROUP BY v.article.nomArticle")
    List<VendeurArticleVenduDTO> getArticlesVendusParAgence(LocalDateTime startOfDay, LocalDateTime endOfDay,
            String nomAgence);

    // ici,c'est le classement des articles les plus vendus
    @Query("SELECT new com.monpleingaz.centralpleingaz01.dto.VendeurArticleVenduDTO(" +
            "v.article.nomArticle, SUM(v.quantite), SUM(v.tonneMetrique)) " +
            "FROM Vente v WHERE v.dateVente >= :startOfDay AND v.dateVente < :endOfDay " +
            "GROUP BY v.article.nomArticle " +
            "ORDER BY SUM(v.quantite) DESC")
    List<VendeurArticleVenduDTO> getClassementArticlesJournalier(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
