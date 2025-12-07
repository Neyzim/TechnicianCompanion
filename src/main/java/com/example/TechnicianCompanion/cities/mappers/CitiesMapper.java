package com.example.TechnicianCompanion.cities.mappers;

import com.example.TechnicianCompanion.cities.dto.CitiesDTO;
import com.example.TechnicianCompanion.cities.models.Cities;
import org.springframework.stereotype.Component;

@Component
public class CitiesMapper {

    //Transforms CITIES DTO -> CITIES ENTITY
    public Cities map(CitiesDTO citiesDTO) {
        Cities city = new Cities();
        city.setName(citiesDTO.getName());
        city.setState(citiesDTO.getState());
        city.setTechnicians_ids(citiesDTO.getTechnicians_ids());
        return city;

    }

    //Transforms CITIES ENTITY -> CITIES DTO
    public CitiesDTO map(Cities cities) {
       CitiesDTO citiesDTO = new CitiesDTO();
       citiesDTO.setName(cities.getName());
       citiesDTO.setState(citiesDTO.getState());
       citiesDTO.setTechnicians_ids(cities.getTechnicians_ids());
       return citiesDTO;
    }

}