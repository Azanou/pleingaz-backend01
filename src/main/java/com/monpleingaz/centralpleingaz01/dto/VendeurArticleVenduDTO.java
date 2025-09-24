package com.monpleingaz.centralpleingaz01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendeurArticleVenduDTO {
    private String nomArticle;
    private Long quantite;
    private Double tonneMetrique;
}
