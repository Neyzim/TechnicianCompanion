package com.example.TechnicianCompanion.reports.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class NetworkInfo {

    private String oltId;
    private String ctoId;
    private String network;
    private String ctoPort;
}
