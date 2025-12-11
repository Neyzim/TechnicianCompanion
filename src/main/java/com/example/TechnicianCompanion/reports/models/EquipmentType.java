package com.example.TechnicianCompanion.reports.models;

import com.example.TechnicianCompanion.reports.dto.ReportDTO;

public enum EquipmentType {
    BRIDGE {
        @Override
        public void getEquipmentFields(Equipment equipment, ReportDTO dto, boolean installed) {
        if(installed) {
            equipment.setOnuModel(dto.getInstalledOnuModel());
            equipment.setOnuSerialNumber(dto.getInstalledOnuSerialNumber());
            equipment.setWifiEquipmentModel(dto.getInstalledWifiEquipmentModel());
            equipment.setWifiEquipmentSN(dto.getInstalledWifiEquipmentSN());
        }else {
            equipment.setOnuModel(dto.getRemovedOnuModel());
            equipment.setOnuSerialNumber(dto.getRemovedOnuSerialNumber());
            equipment.setWifiEquipmentModel(dto.getRemovedWifiEquipmentModel());
            equipment.setWifiEquipmentSN(dto.getRemovedWifiEquipmentSN());
        }
        }
    },


    ROUTER {
        @Override
        public void getEquipmentFields(Equipment equipment, ReportDTO dto, boolean installed) {
        if(installed) {
            equipment.setOnuModel(dto.getInstalledOnuModel());
            equipment.setOnuSerialNumber(dto.getInstalledOnuSerialNumber());
        }else{
            equipment.setOnuModel(dto.getRemovedOnuModel());
            equipment.setOnuSerialNumber(dto.getRemovedOnuSerialNumber());
        }
        }
    };

    public abstract void getEquipmentFields(Equipment equipment, ReportDTO dto, boolean installed);
}


