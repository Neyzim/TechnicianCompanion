package com.example.TechnicianCompanion.reports.service;

import com.example.TechnicianCompanion.authentication.models.User;

import com.example.TechnicianCompanion.reports.models.EquipmentType;
import com.example.TechnicianCompanion.reports.models.Report;
import com.example.TechnicianCompanion.reports.models.ReportTypes;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class BuildReportsService {


    public StringBuilder stringBuilder(Report report) {

        String cityName = report.getCity() != null ? report.getCity().getName() : "Cidade não definida";
        StringBuilder reportActivationBuilded = new StringBuilder();

        if (report.getType() == ReportTypes.ATIVACAO) {
            reportActivationBuilded.append(report.getType())
                    .append(" / " + cityName)
                    .append("\n");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = report.getDayToday().format(formatter);
            reportActivationBuilded.append("DATA: " + dataFormatada + "\n");

            reportActivationBuilded.append("\n");
            reportActivationBuilded.append("------------------------------------\n");
            reportActivationBuilded.append("PROTOCOLO: " + report.getProtocolNumber() + "\n");
            reportActivationBuilded.append("CLIENTE: " + report.getClient() + "\n");
            reportActivationBuilded.append("------------------------------------\n");
            reportActivationBuilded.append("INFORMAÇÕES EQUIPAMENTOS:\n");
            reportActivationBuilded.append("EQUIPAMENTOS INSTALADOS:\n");

            if (report.getInstalledEquipment() != null) {
                reportActivationBuilded.append("MODELO: " + report.getInstalledEquipment().getOnuModel() + "\n");
                reportActivationBuilded.append("SERIAL: " + report.getInstalledEquipment().getOnuSerialNumber() + "\n");

                if (report.getInstalledEquipment().getEquipmentType() == EquipmentType.BRIDGE) {
                    reportActivationBuilded.append("MODELO WIFI: " + report.getInstalledEquipment().getWifiEquipmentModel() + "\n");
                    reportActivationBuilded.append("SERIAL WIFI: " + report.getInstalledEquipment().getWifiEquipmentSN() + "\n");
                }
            }

            reportActivationBuilded.append("------------------------------------\n");
            reportActivationBuilded.append("DADOS DA CONEXÃO:\n");
            if (report.getNetworkInfo() != null) {
                reportActivationBuilded.append("OLT: " + report.getNetworkInfo().getOltId() + "\n");
                reportActivationBuilded.append("CAIXA: " + report.getNetworkInfo().getCtoId() + "\n");
                reportActivationBuilded.append("REDE: " + report.getNetworkInfo().getNetwork() + "\n");
                reportActivationBuilded.append("PORTA: " + report.getNetworkInfo().getCtoPort() + "\n");
            }
            reportActivationBuilded.append("------------------------------------\n");
            reportActivationBuilded.append("MATERIAIS USADOS:\n");
            if (report.getUsedMaterials() != null) {
                reportActivationBuilded.append("DROP: " + report.getUsedMaterials().getDrop() + "\n");
                reportActivationBuilded.append("CONECTOR: " + report.getUsedMaterials().getConector() + "\n");
                reportActivationBuilded.append("ALÇAS: " + report.getUsedMaterials().getAlcas() + "\n");
                reportActivationBuilded.append("OLHAL: " + report.getUsedMaterials().getOlhal() + "\n");
            }
            reportActivationBuilded.append("------------------------------------\n");
            reportActivationBuilded.append("Tecnicos:\n");

            for (User technician : report.getTechnicians()) {
                String technicianName = technician.getName();
                reportActivationBuilded.append("- ").append(technicianName).append("\n");
            }
        }
        return reportActivationBuilded;
    }
}
