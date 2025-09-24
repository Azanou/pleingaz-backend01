package com.monpleingaz.centralpleingaz01.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgenceDetailsDTO {
    private String nomAgence;
    private Double totalVentes;
    private List<VendeurArticleVenduDTO> articlesVendus;

}
