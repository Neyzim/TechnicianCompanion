package com.example.TechnicianCompanion.reports.dto;

import com.example.TechnicianCompanion.cities.models.Cities;
import com.example.TechnicianCompanion.reports.models.EquipmentType;
import com.example.TechnicianCompanion.reports.models.ReportTypes;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private Long protocolNumber;
    private Long city_id;
    private LocalDate today;
    private ReportTypes type;
    private String broadbandPlan;
    private String client;
    private String costumerReport;
    private String problem;
    private String solution;

    private EquipmentType equipmentInstalled;
    private String installedOnuSerialNumber;
    private String installedOnuModel;
    private String installedWifiEquipmentSN;
    private String installedWifiEquipmentModel;

    private EquipmentType removedEquipment;
    private String removedOnuSerialNumber;
    private String removedOnuModel;
    private String removedWifiEquipmentSN;
    private String removedWifiEquipmentModel;

    private String oltId;
    private String ctoId;
    private String network;
    private String ctoPort;

    private Integer drop;
    private Integer conector;
    private Integer alcas;
    private Integer barraRoscada;
    private Integer olhal;

    private List<String> user_ids;
}