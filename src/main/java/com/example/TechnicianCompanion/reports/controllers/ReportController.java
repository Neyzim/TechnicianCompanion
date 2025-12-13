package com.example.TechnicianCompanion.reports.controllers;

import com.example.TechnicianCompanion.reports.dto.ReportDTO;
import com.example.TechnicianCompanion.reports.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveNewReport(@RequestBody ReportDTO reportDTO){
        ReportDTO createdReport = reportService.createNewReport(reportDTO);
        return ResponseEntity.ok().body(createdReport);
    }
}
