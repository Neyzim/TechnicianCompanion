package com.example.TechnicianCompanion.reports.repositories;

import com.example.TechnicianCompanion.reports.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReportsRepository extends JpaRepository<Report, Long> {
    List<Report> findByUser_id(String userId);
}
