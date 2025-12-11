package com.example.TechnicianCompanion.reports.repositories;

import com.example.TechnicianCompanion.reports.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportsRepository extends JpaRepository<Report, Long> {
}
