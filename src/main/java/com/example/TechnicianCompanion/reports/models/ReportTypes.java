package com.example.TechnicianCompanion.reports.models;

public enum ReportTypes {

    ATIVACAO("Ativação"),
    MUDANCA_DE_ENDERECO("Mudança de Endereço"),
    MANUTENCAO("Manutenção"),
    RECOLHIMENTO_DE_EQUIPAMENTOS("Recolhimento de Equipamentos"),
    ATIVACAO_EQUIPE_REDE("Ativação equipe de rede"),
    ROMPIMENTO_EQUIPE_DE_REDE("Rompimento");

    private final String type;

    ReportTypes(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }
}
