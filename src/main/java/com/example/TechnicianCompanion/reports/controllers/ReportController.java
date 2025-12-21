package com.example.TechnicianCompanion.reports.controllers;

import com.example.TechnicianCompanion.reports.dto.ReportDTO;
import com.example.TechnicianCompanion.reports.dto.ReportResponseDTO;
import com.example.TechnicianCompanion.reports.models.Report;
import com.example.TechnicianCompanion.reports.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Salva um novo Report",
            description = "Esta rota salva um novo report, que pode ser de vários tipos no banco de dados")
    @ApiResponse(responseCode = "200", description = "Relatorio criado e salvo no Banco de dados!")
    public ResponseEntity<?> saveNewReport(@RequestBody ReportDTO reportDTO){
        ReportDTO createdReport = reportService.createNewReport(reportDTO);
        return ResponseEntity.ok().body(createdReport);
    }

    @GetMapping(value = "/list/{protocolNumber}")
    @Operation(summary = "Busca um Report e o retorna formatado em String",
                description = "Recebe um numero de protocolo, faz a busca desse protocolo no banco de dados e retorna o protocolo Formatado!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report encontrado!"),
            @ApiResponse(responseCode = "404", description = "Relatório não foi encontrado no banco de Dados!")
    })
    public ResponseEntity<String> listReportByID(
            @Parameter(description = "Usuario envia o numero de protocolo para busca")
            @PathVariable Long protocolNumber){

        Optional<Report> findedReport = reportService.findById(protocolNumber);
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

    @GetMapping("/list/user/{userId}")
    public ResponseEntity<List<ReportResponseDTO>> listReportsByTechnician(@PathVariable String userId){
        List<ReportResponseDTO> reportResponseDTOList = reportService.getReportsByUser(userId);
        return ResponseEntity.ok().body(reportResponseDTOList);
    }

    @Operation(summary = "Obtém um protocolo, mas limitando por usuário que estejam citados no mesmo")
    @GetMapping(value = "/get/{protocolNumber}")
    public ResponseEntity<ReportResponseDTO> findReportLimitedByLoggedUser(@PathVariable Long protocolNumber){
        ResponseEntity<ReportResponseDTO> foundReport = reportService.findReportLimitedByLoggedUser(protocolNumber);
        return foundReport;
    }

}
