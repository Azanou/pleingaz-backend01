package com.monpleingaz.centralpleingaz01.dto;

import java.time.LocalDate;



import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DayResumeDTO {

    private LocalDate date;
    private Double netAPayer;
    private Double payerComptant;
    private Double creance;
    private Double recouvrement;
    private Double avanceCommande;

      public DayResumeDTO(
            LocalDate date,
            Double netAPayer,
            Double payerComptant,
            Double creance,
            Double recouvrement,
            Double avanceCommande) {
        this.date = date;
        this.netAPayer = netAPayer;
        this.payerComptant = payerComptant;
        this.creance = creance;
        this.recouvrement = recouvrement;
        this.avanceCommande = avanceCommande;
    }
}

