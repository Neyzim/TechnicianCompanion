package com.example.TechnicianCompanion.reports.controllers;

import com.example.TechnicianCompanion.reports.dto.ReportDTO;
import com.example.TechnicianCompanion.reports.models.Report;
import com.example.TechnicianCompanion.reports.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<String> listReportByID(@PathVariable Long id){
        Optional<Report> findedReport = reportService.findById(id);
        if(findedReport.isPresent()){
            String formattedReport = reportService.reportFormatedToReturn(findedReport.get());
            return ResponseEntity.ok(formattedReport);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Relatório não encontrado");
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<ReportDTO>> listAllReports(){
        List<ReportDTO> reportList = reportService.listAllReports();
        return ResponseEntity.ok().body(reportList);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteReportById(@PathVariable Long id){
        reportService.deleteReportById(id);
    }
}
