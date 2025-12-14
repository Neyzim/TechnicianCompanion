package com.example.TechnicianCompanion.reports.service;

import com.example.TechnicianCompanion.authentication.models.User;
import com.example.TechnicianCompanion.authentication.repositories.UserRepository;
import com.example.TechnicianCompanion.cities.models.Cities;
import com.example.TechnicianCompanion.cities.repositories.CitiesRepository;
import com.example.TechnicianCompanion.cities.service.CitiesService;
import com.example.TechnicianCompanion.reports.dto.ReportDTO;
import com.example.TechnicianCompanion.reports.mapper.ReportMapper;
import com.example.TechnicianCompanion.reports.models.Equipment;
import com.example.TechnicianCompanion.reports.models.Report;
import com.example.TechnicianCompanion.reports.repositories.ReportsRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportsRepository reportsRepository;
    private final ReportMapper reportMapper;
    private final UserRepository userRepository;
    private final CitiesService citiesService;
    private final BuildReportsService buildReportsService;

    public ReportService(ReportsRepository reportsRepository, ReportMapper reportMapper, UserRepository userRepository, CitiesService citiesService, BuildReportsService buildReportsService) {
        this.reportsRepository = reportsRepository;
        this.reportMapper = reportMapper;
        this.userRepository = userRepository;
        this.citiesService = citiesService;
        this.buildReportsService = buildReportsService;
    }


    //todo return  city name and user names
    public ReportDTO createNewReport(ReportDTO dto){
        Report reportToSave = reportMapper.map(dto);

        LocalDate dataHoje = LocalDate.now();
        reportToSave.setDayToday(dataHoje);

        if(dto.getEquipmentInstalled() != null){
            Equipment installed = new Equipment();
            dto.getEquipmentInstalled().getEquipmentFields(installed, dto, true);
            reportToSave.setInstalledEquipment(installed);
        }
        if(dto.getRemovedEquipment() != null){
            Equipment removed = new Equipment();
            dto.getRemovedEquipment().getEquipmentFields(removed, dto, false);
            reportToSave.setRemovedEquipment(removed);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User creator = (User) authentication.getPrincipal();
        reportToSave.getTechnicians().add(creator);

        if(dto.getUser_ids()!= null){
            dto.getUser_ids().forEach(userId ->{
                User technician = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("Tecnico Desconhecido"));
                        reportToSave.getTechnicians().add(technician);
            });
        }

        Cities findedCity = citiesService.findCityById(dto.getCity_id())
                .orElseThrow(null);
        reportToSave.setCity(findedCity);

        reportsRepository.save(reportToSave);
        return reportMapper.map(reportToSave);
    }


    public Optional<Report> findById(Long id){
        Optional<Report> foundReport = reportsRepository.findById(id);
        if(foundReport.isPresent()){
            return foundReport;
        }else {
            return Optional.empty();
        }
    }

    public List<ReportDTO> listAllReports(){
        List<Report> reportList = reportsRepository.findAll();
        return reportList.stream()
                .map(reportMapper::map)
                .collect(Collectors.toList());
    }

    public void deleteReportById(Long id){
        reportsRepository.deleteById(id);
    }

    public String reportFormatedToReturn(Report report) {
        StringBuilder builder = buildReportsService.stringBuilder(report);
        return builder.toString();
    }

}
