package com.example.TechnicianCompanion.reports.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class NetworkInfo {

    private String oltId;
    private String ctoId;
    private String network;
    private String ctoPort;
}
