package com.example.TechnicianCompanion.reports.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Materials {

    private Integer drop;
    private Integer conector;
    private Integer alcas;
    private Integer barraRoscada;
    private Integer olhal;
}
