package com.example.TechnicianCompanion.cities.repositories;

import com.example.TechnicianCompanion.cities.models.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository extends JpaRepository<Cities, Long> {

}
