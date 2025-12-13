package com.example.TechnicianCompanion.cities.service;

import com.example.TechnicianCompanion.cities.dto.CitiesDTO;
import com.example.TechnicianCompanion.cities.mappers.CitiesMapper;
import com.example.TechnicianCompanion.cities.models.Cities;
import com.example.TechnicianCompanion.cities.repositories.CitiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitiesService {

    private final CitiesRepository citiesRepository;
    private final CitiesMapper citiesMapper;


    public CitiesService(CitiesRepository citiesRepository, CitiesMapper citiesMapper) {
        this.citiesRepository = citiesRepository;
        this.citiesMapper = citiesMapper;
    }

    public Optional<Cities> findCityById(Long id){
        Optional<Cities> findedCity = citiesRepository.findById(id);
        if (findedCity.isPresent()){
            return findedCity;
        }else {
            return Optional.empty();
        }
    }

    public CitiesDTO createCity(CitiesDTO cityDTO){
        Cities citieToSave = citiesMapper.map(cityDTO);
        citiesRepository.save(citieToSave);
        return citiesMapper.map(citieToSave);
    }

    public void DeleteCityById(Long id){
      citiesRepository.deleteById(id);
    }

    public List<CitiesDTO> listAllCities(){
        List<Cities> citiesList = citiesRepository.findAll();
        return citiesList.stream()
                .map(citiesMapper::map)
                .collect(Collectors.toList());
    }

    public CitiesDTO updateCity(Long id, CitiesDTO toUpdatedCity){
        Optional<Cities> findCity = citiesRepository.findById(id);
        if (findCity.isPresent()){
            Cities updatedCity = citiesMapper.map(toUpdatedCity);
            updatedCity.setId(id);
            Cities savedCity = citiesRepository.save(updatedCity);
            return citiesMapper.map(savedCity);
        }else {
            return null;
        }
    }

    public Optional<String> findCityByName(Long id){
        Optional<Cities> findCity = findCityById(id);
        return findCity.map(Cities::getName);
    }
}
