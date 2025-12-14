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
        StringBuilder reportStringBuilded = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = report.getDayToday().format(formatter);

        if (report.getType() == ReportTypes.ATIVACAO) {
            reportStringBuilded.append(report.getType())
                    .append(" / " + cityName.toUpperCase())
                    .append("\n");
            reportStringBuilded.append("\n");

            reportStringBuilded.append("DATA: " + dataFormatada + "\n");
            reportStringBuilded.append("\n");
            reportStringBuilded.append("------------------------------------\n");
            reportStringBuilded.append("PROTOCOLO: " + report.getProtocolNumber() + "\n");
            reportStringBuilded.append("\n");
            reportStringBuilded.append("CLIENTE: " + report.getClient() + "\n");
            reportStringBuilded.append("------------------------------------\n");
            reportStringBuilded.append("INFORMAÇÕES EQUIPAMENTOS:\n");
            reportStringBuilded.append("\n");
            reportStringBuilded.append("EQUIPAMENTOS INSTALADOS:\n");
            reportStringBuilded.append("\n");
            if (report.getInstalledEquipment() != null) {
                reportStringBuilded.append("MODELO: " + report.getInstalledEquipment().getOnuModel() + "\n");
                reportStringBuilded.append("SERIAL: " + report.getInstalledEquipment().getOnuSerialNumber() + "\n");

                if (report.getInstalledEquipment().getEquipmentType() == EquipmentType.BRIDGE) {
                    reportStringBuilded.append("MODELO WIFI: " + report.getInstalledEquipment().getWifiEquipmentModel() + "\n");
                    reportStringBuilded.append("SERIAL WIFI: " + report.getInstalledEquipment().getWifiEquipmentSN() + "\n");
                }

                reportStringBuilded.append("\n");
                if (report.getRemovedEquipment() != null) {
                    reportStringBuilded.append("EQUIPAMENTOS REMOVIDOS:\n");
                    reportStringBuilded.append("\n");
                    reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getOnuModel() + "\n");
                    reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getOnuSerialNumber() + "\n");
                    if (report.getInstalledEquipment().getEquipmentType() == EquipmentType.BRIDGE) {
                        reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getWifiEquipmentModel() + "\n");
                        reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getWifiEquipmentSN() + "\n");
                    }
                }
                reportStringBuilded.append("\n");
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("DADOS DA CONEXÃO:\n");
                reportStringBuilded.append("\n");
                if (report.getNetworkInfo() != null) {
                    reportStringBuilded.append("OLT: " + report.getNetworkInfo().getOltId() + "\n");
                    reportStringBuilded.append("CAIXA: " + report.getNetworkInfo().getCtoId() + "\n");
                    reportStringBuilded.append("REDE: " + report.getNetworkInfo().getNetwork() + "\n");
                    reportStringBuilded.append("PORTA: " + report.getNetworkInfo().getCtoPort() + "\n");
                }
                reportStringBuilded.append("\n");
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("MATERIAIS USADOS:\n");
                reportStringBuilded.append("\n");
                if (report.getUsedMaterials() != null) {
                    reportStringBuilded.append("DROP: " + report.getUsedMaterials().getDrop() + "\n");
                    reportStringBuilded.append("CONECTOR: " + report.getUsedMaterials().getConector() + "\n");
                    reportStringBuilded.append("ALÇAS: " + report.getUsedMaterials().getAlcas() + "\n");
                    reportStringBuilded.append("OLHAL: " + report.getUsedMaterials().getOlhal() + "\n");
                }
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("Tecnicos:\n");

                for (User technician : report.getTechnicians()) {
                    String technicianName = technician.getName();
                    reportStringBuilded.append("- ").append(technicianName).append("\n");
                }
            }
        }
            if (report.getType() == ReportTypes.MANUTENCAO) {
                reportStringBuilded.append(report.getType() + " / " + cityName.toUpperCase());
                reportStringBuilded.append("\n");
                reportStringBuilded.append("DATA: " + dataFormatada + "\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("PROTOCOLO: " + report.getProtocolNumber() + "\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("CLIENTE: " + report.getClient() + "\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("RELATO DO CLIENTE: " + report.getCostumerReport()+ "\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("PROBLEMA ENCONTRADO: " + report.getProblem()+ "\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("O QUE FOI FEITO PARA SOLUCIONAR O PROBLEMA? " + report.getSolution()+ "\n");
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("INFORMAÇÕES EQUIPAMENTOS:\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("EQUIPAMENTOS INSTALADOS:\n");
                reportStringBuilded.append("\n");
                if (report.getInstalledEquipment() != null) {
                    reportStringBuilded.append("MODELO: " + report.getInstalledEquipment().getOnuModel() + "\n");
                    reportStringBuilded.append("SERIAL: " + report.getInstalledEquipment().getOnuSerialNumber() + "\n");

                    if (report.getInstalledEquipment().getEquipmentType() == EquipmentType.BRIDGE) {
                        reportStringBuilded.append("MODELO WIFI: " + report.getInstalledEquipment().getWifiEquipmentModel() + "\n");
                        reportStringBuilded.append("SERIAL WIFI: " + report.getInstalledEquipment().getWifiEquipmentSN() + "\n");
                    }
                }

                reportStringBuilded.append("\n");
                if (report.getRemovedEquipment() != null) {
                    reportStringBuilded.append("\n");
                    reportStringBuilded.append("EQUIPAMENTOS REMOVIDOS:\n");
                    reportStringBuilded.append("\n");
                    reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getOnuModel() + "\n");
                    reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getOnuSerialNumber() + "\n");
                    if (report.getInstalledEquipment().getEquipmentType() == EquipmentType.BRIDGE) {
                        reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getWifiEquipmentModel()+ "\n");
                        reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getWifiEquipmentSN()+ "\n");
                    }
                }
                reportStringBuilded.append("\n");
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("DADOS DA CONEXÃO:\n");
                reportStringBuilded.append("\n");
                if (report.getNetworkInfo() != null) {
                    reportStringBuilded.append("OLT: " + report.getNetworkInfo().getOltId() + "\n");
                    reportStringBuilded.append("CAIXA: " + report.getNetworkInfo().getCtoId() + "\n");
                    reportStringBuilded.append("REDE: " + report.getNetworkInfo().getNetwork() + "\n");
                    reportStringBuilded.append("PORTA: " + report.getNetworkInfo().getCtoPort() + "\n");
                }
                reportStringBuilded.append("\n");
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("MATERIAIS USADOS:\n");
                reportStringBuilded.append("\n");
                if (report.getUsedMaterials() != null) {
                    reportStringBuilded.append("DROP: " + report.getUsedMaterials().getDrop() + "\n");
                    reportStringBuilded.append("CONECTOR: " + report.getUsedMaterials().getConector() + "\n");
                    reportStringBuilded.append("ALÇAS: " + report.getUsedMaterials().getAlcas() + "\n");
                    reportStringBuilded.append("OLHAL: " + report.getUsedMaterials().getOlhal() + "\n");
                }
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("Tecnicos:\n");

                for (User technician : report.getTechnicians()) {
                    String technicianName = technician.getName();
                    reportStringBuilded.append("- ").append(technicianName).append("\n");
                }
            }
            if (report.getType() == ReportTypes.MUDANCA_DE_ENDERECO) {
                reportStringBuilded.append(report.getType() + " / " + cityName.toUpperCase());
                reportStringBuilded.append("\n");
                reportStringBuilded.append("DATA: " + dataFormatada + "\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("PROTOCOLO: " + report.getProtocolNumber() + "\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("CLIENTE: " + report.getClient() + "\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("RELATO DO CLIENTE: " + report.getCostumerReport() + "\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("PROBLEMA ENCONTRADO: " + report.getProblem() + "\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("DECLARO QUE REFORCEI COM O CLIENTE A NECESSIDADE DO PAGAMENTO DA TAXA DE MUDANÇA DE ENDEREÇO E DE CUSTO DE CABO, CASO SEJA USADO\n");
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("INFORMAÇÕES EQUIPAMENTOS:\n");
                reportStringBuilded.append("\n");
                reportStringBuilded.append("EQUIPAMENTOS INSTALADOS:\n");
                reportStringBuilded.append("\n");
                if (report.getInstalledEquipment() != null) {
                    reportStringBuilded.append("MODELO: " + report.getInstalledEquipment().getOnuModel() + "\n");
                    reportStringBuilded.append("SERIAL: " + report.getInstalledEquipment().getOnuSerialNumber() + "\n");

                    if (report.getInstalledEquipment().getEquipmentType() == EquipmentType.BRIDGE) {
                        reportStringBuilded.append("MODELO WIFI: " + report.getInstalledEquipment().getWifiEquipmentModel() + "\n");
                        reportStringBuilded.append("SERIAL WIFI: " + report.getInstalledEquipment().getWifiEquipmentSN() + "\n");
                    }
                }
                reportStringBuilded.append("\n");

                if (report.getRemovedEquipment() != null) {
                    reportStringBuilded.append("EQUIPAMENTOS REMOVIDOS:\n");
                    reportStringBuilded.append("\n");
                    reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getOnuModel() + "\n");
                    reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getOnuSerialNumber() + "\n");
                    if (report.getInstalledEquipment().getEquipmentType() == EquipmentType.BRIDGE) {
                        reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getWifiEquipmentModel()+ "\n");
                        reportStringBuilded.append("MODELO: " + report.getRemovedEquipment().getWifiEquipmentSN()+ "\n");
                    }
                }
                reportStringBuilded.append("\n");
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("DADOS DA CONEXÃO:\n");
                reportStringBuilded.append("\n");
                if (report.getNetworkInfo() != null) {
                    reportStringBuilded.append("OLT: " + report.getNetworkInfo().getOltId() + "\n");
                    reportStringBuilded.append("CAIXA: " + report.getNetworkInfo().getCtoId() + "\n");
                    reportStringBuilded.append("REDE: " + report.getNetworkInfo().getNetwork() + "\n");
                    reportStringBuilded.append("PORTA: " + report.getNetworkInfo().getCtoPort() + "\n");
                }
                reportStringBuilded.append("\n");
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("MATERIAIS USADOS:\n");
                reportStringBuilded.append("\n");
                if (report.getUsedMaterials() != null) {
                    reportStringBuilded.append("DROP: " + report.getUsedMaterials().getDrop() + "\n");
                    reportStringBuilded.append("CONECTOR: " + report.getUsedMaterials().getConector() + "\n");
                    reportStringBuilded.append("ALÇAS: " + report.getUsedMaterials().getAlcas() + "\n");
                    reportStringBuilded.append("OLHAL: " + report.getUsedMaterials().getOlhal() + "\n");
                }
                reportStringBuilded.append("------------------------------------\n");
                reportStringBuilded.append("Tecnicos:\n");

                for (User technician : report.getTechnicians()) {
                    String technicianName = technician.getName();
                    reportStringBuilded.append("- ").append(technicianName).append("\n");
                }
            }
        return reportStringBuilded;
    }
}