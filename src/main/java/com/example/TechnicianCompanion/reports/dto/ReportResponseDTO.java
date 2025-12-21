package com.example.TechnicianCompanion.reports.dto;

import com.example.TechnicianCompanion.reports.models.ReportTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportResponseDTO {
    private ReportTypes type;
    private String clientName;
    private LocalDate date;
    private Long protocolNumber;
}
