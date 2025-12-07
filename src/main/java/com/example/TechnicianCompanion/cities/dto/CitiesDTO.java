package com.example.TechnicianCompanion.cities.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CitiesDTO{

        private Long id;
        private String name;
        private String state;
        private Integer technicians_ids;
}
