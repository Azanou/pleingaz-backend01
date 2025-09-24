package com.monpleingaz.centralpleingaz01.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendeurDetailsDTO {
    private String nomVendeur;
    private Double totalVentes;
    private List<VendeurArticleVenduDTO> articlesVendus;
}
