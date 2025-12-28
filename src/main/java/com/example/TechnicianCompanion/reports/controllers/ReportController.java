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
    public ResponseEntity<String> listReportByID(@PathVariable Long protocolNumber){
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
    @Operation(summary = "Lista todos os reports", description = "Lista todos os reports independetemente de User, somente Admin e SUpervisor")
    public ResponseEntity<List<ReportDTO>> listAllReports(){
        List<ReportDTO> reportList = reportService.listAllReports();
        return ResponseEntity.ok().body(reportList);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deleta um Report", description = "Deleta um report, Somente Admin e Supervisor")
    public void deleteReportById(@PathVariable Long id){
        reportService.deleteReportById(id);
    }

    @GetMapping("/list/user/{userId}")
    @Operation(summary = "Lista todos os Reports de um User",
            description = "Lista todos os protocolos de um determinado user, somente Admin e Supervisor tem acesso.")
    public ResponseEntity<List<ReportResponseDTO>> listReportsByTechnician(@PathVariable String userId){
        List<ReportResponseDTO> reportResponseDTOList = reportService.getReportsByUser(userId);
        return ResponseEntity.ok().body(reportResponseDTOList);
    }


    @GetMapping(value = "/get/{protocolNumber}")
    @Operation(summary = "Obtém um protocolo do usuario logado.",
            description = "Obtem um protocolo de uma lista de protocolos que pertencem ao usuario logado " +
                    "(Principal endpoint dos Tecnicos)")
    public ResponseEntity<ReportResponseDTO> findReportLimitedByLoggedUser(@PathVariable Long protocolNumber){
        ResponseEntity<ReportResponseDTO> foundReport = reportService.findReportLimitedByLoggedUser(protocolNumber);
        return foundReport;
    }

}
