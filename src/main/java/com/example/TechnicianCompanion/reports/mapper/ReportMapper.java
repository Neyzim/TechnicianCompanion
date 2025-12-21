package com.example.TechnicianCompanion.reports.mapper;

import com.example.TechnicianCompanion.authentication.models.User;
import com.example.TechnicianCompanion.cities.models.Cities;
import com.example.TechnicianCompanion.cities.repositories.CitiesRepository;
import com.example.TechnicianCompanion.reports.dto.ReportDTO;
import com.example.TechnicianCompanion.reports.dto.ReportResponseDTO;
import com.example.TechnicianCompanion.reports.models.Equipment;
import com.example.TechnicianCompanion.reports.models.Materials;
import com.example.TechnicianCompanion.reports.models.NetworkInfo;
import com.example.TechnicianCompanion.reports.models.Report;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReportMapper {


    private final CitiesRepository citiesRepository;

    public ReportMapper(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    public Report map (ReportDTO dto ){
        Report report = new Report();
        report.setProtocolNumber(dto.getProtocolNumber());
        Cities city = citiesRepository.findById(dto.getCity_id()).orElseThrow(null);
        report.setCity(city);
        report.setType(dto.getType());
        report.setBroadbandPlan(dto.getBroadbandPlan());
        report.setClient(dto.getClient());
        report.setCostumerReport(dto.getCostumerReport());
        report.setProblem(dto.getProblem());
        report.setSolution(dto.getSolution());

        Equipment installed = new Equipment();
        installed.setEquipmentType(dto.getEquipmentInstalled());
        installed.setOnuSerialNumber(dto.getInstalledOnuSerialNumber());
        installed.setOnuModel(dto.getInstalledOnuModel());
        installed.setWifiEquipmentSN(dto.getInstalledWifiEquipmentSN());
        installed.setWifiEquipmentModel(dto.getInstalledWifiEquipmentModel());
        report.setInstalledEquipment(installed);

        Equipment removed = new Equipment();
        removed.setEquipmentType(dto.getRemovedEquipment());
        removed.setOnuSerialNumber(dto.getRemovedOnuSerialNumber());
        removed.setOnuModel(dto.getRemovedOnuModel());
        removed.setWifiEquipmentSN(dto.getRemovedWifiEquipmentSN());
        removed.setWifiEquipmentModel(dto.getRemovedWifiEquipmentModel());
        report.setRemovedEquipment(removed);

        NetworkInfo networkInfo = new NetworkInfo();
        networkInfo.setOltId(dto.getOltId());
        networkInfo.setCtoId(dto.getCtoId());
        networkInfo.setNetwork(dto.getNetwork());
        networkInfo.setCtoPort(dto.getCtoPort());
        report.setNetworkInfo(networkInfo);

        Materials materials = new Materials();
        materials.setDrop(dto.getDrop());
        materials.setConector(dto.getConector());
        materials.setAlcas(dto.getAlcas());
        materials.setBarraRoscada(dto.getBarraRoscada());
        materials.setOlhal(dto.getOlhal());
        report.setUsedMaterials(materials);

        return report;
    }

    public ReportDTO map(Report report) {
        ReportDTO dto = new ReportDTO();

        // Campos simples
        dto.setProtocolNumber(report.getProtocolNumber());
        dto.setCity_id(report.getCity().getId());
        dto.setType(report.getType());
        dto.setBroadbandPlan(report.getBroadbandPlan());
        dto.setClient(report.getClient());
        dto.setCostumerReport(report.getCostumerReport());
        dto.setProblem(report.getProblem());
        dto.setSolution(report.getSolution());

        if (report.getInstalledEquipment() != null) {
            dto.setEquipmentInstalled(report.getInstalledEquipment().getEquipmentType());
            dto.setInstalledOnuSerialNumber(report.getInstalledEquipment().getOnuSerialNumber());
            dto.setInstalledOnuModel(report.getInstalledEquipment().getOnuModel());
            dto.setInstalledWifiEquipmentSN(report.getInstalledEquipment().getWifiEquipmentSN());
            dto.setInstalledWifiEquipmentModel(report.getInstalledEquipment().getWifiEquipmentModel());
        }

        if (report.getRemovedEquipment() != null) {
            dto.setRemovedEquipment(report.getRemovedEquipment().getEquipmentType());
            dto.setRemovedOnuSerialNumber(report.getRemovedEquipment().getOnuSerialNumber());
            dto.setRemovedOnuModel(report.getRemovedEquipment().getOnuModel());
            dto.setRemovedWifiEquipmentSN(report.getRemovedEquipment().getWifiEquipmentSN());
            dto.setRemovedWifiEquipmentModel(report.getRemovedEquipment().getWifiEquipmentModel());
        }

        // Network Info
        if (report.getNetworkInfo() != null) {
            dto.setOltId(report.getNetworkInfo().getOltId());
            dto.setCtoId(report.getNetworkInfo().getCtoId());
            dto.setNetwork(report.getNetworkInfo().getNetwork());
            dto.setCtoPort(report.getNetworkInfo().getCtoPort());
        }

        // Materiais
        if (report.getUsedMaterials() != null) {
            dto.setDrop(report.getUsedMaterials().getDrop());
            dto.setConector(report.getUsedMaterials().getConector());
            dto.setAlcas(report.getUsedMaterials().getAlcas());
            dto.setBarraRoscada(report.getUsedMaterials().getBarraRoscada());
            dto.setOlhal(report.getUsedMaterials().getOlhal());
        }

        if (report.getUser() != null) {
            dto.setUser_ids(
                    report.getUser().stream()
                            .map(User::getId)
                            .collect(Collectors.toList())
            );
        }
        dto.setUser_ids(
                report.getUser().stream()
                        .map(User::getId)
                        .toList()
        );

        return dto;
    }

    public Report mapDtoToEntity(ReportResponseDTO dto) {
        Report report = new Report();
        report.setType(dto.getType());
        report.setClient(dto.getClientName());
        report.setDayToday(dto.getDate());

        return report;
    }

    public ReportResponseDTO mapEntityToDTO(Report report){
        ReportResponseDTO dto = new ReportResponseDTO();
        dto.setType(report.getType());
        dto.setDate(report.getDayToday());
        dto.setClientName(report.getClient());
        dto.setProtocolNumber(report.getProtocolNumber());

        return dto;
    }
}

