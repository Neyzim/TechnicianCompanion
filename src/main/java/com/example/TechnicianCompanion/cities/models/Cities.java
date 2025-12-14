package com.example.TechnicianCompanion.cities.models;

import com.example.TechnicianCompanion.reports.models.Report;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Cities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String state;
    private Integer technicians_ids;
    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    private List<Report> reports;
}
