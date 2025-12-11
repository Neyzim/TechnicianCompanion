package com.example.TechnicianCompanion.reports.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {

    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;
    private String onuSerialNumber;
    private String onuModel;
    private String wifiEquipmentSN;
    private String wifiEquipmentModel;
}
