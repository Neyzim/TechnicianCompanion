package com.example.TechnicianCompanion.reports.dto;

import com.example.TechnicianCompanion.reports.models.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

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

}