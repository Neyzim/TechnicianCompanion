package com.example.TechnicianCompanion.reports.models;

public enum ReportTypes {

    ATIVACAO("ATIVAÇÃO"),
    MUDANCA_DE_ENDERECO("MUDANÇA DE ENDEREÇO"),
    MANUTENCAO("MANUTENÇÃO"),
    RECOLHIMENTO_DE_EQUIPAMENTOS("RECOLHIMENTO DE EQUIPAMENTOS"),
    ATIVACAO_EQUIPE_REDE("ATIVAÇÃO DE EQUIPE DE REDE"),
    ROMPIMENTO_EQUIPE_DE_REDE("ROMPIMENTO");

    private final String type;

    ReportTypes(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }
    @Override
    public String toString(){
        return type;
    }
}
