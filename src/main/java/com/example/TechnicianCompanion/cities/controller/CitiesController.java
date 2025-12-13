package com.example.TechnicianCompanion.cities.controller;

import com.example.TechnicianCompanion.cities.dto.CitiesDTO;
import com.example.TechnicianCompanion.cities.models.Cities;
import com.example.TechnicianCompanion.cities.service.CitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    private final CitiesService citiesService;

    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CitiesDTO> createCity(@RequestBody CitiesDTO city){
        CitiesDTO createdCity = citiesService.createCity(city);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdCity);
    }

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<?> findCityById(@PathVariable Long id){
        Optional<Cities> findedCity = citiesService.findCityById(id);
        if (findedCity != null){
            return ResponseEntity.ok(findedCity);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cidade não encontrada" + id);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteCityByID(@PathVariable Long id){
        if (citiesService.findCityById(id).isPresent()){
            citiesService.DeleteCityById(id);
            return ResponseEntity.ok("Cidade Deletada: " + id);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A codade com i ID " + id + " não foi encotrada!");
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<CitiesDTO>> listAllCities(){
        List<CitiesDTO> citiesList = citiesService.listAllCities();
        return ResponseEntity.ok(citiesList);
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCityUsingId(@PathVariable Long id, @RequestBody CitiesDTO cityUpdated){
        CitiesDTO findCity = citiesService.updateCity(id, cityUpdated);
        if(findCity != null){
            return ResponseEntity.ok(findCity);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cidade com o ID " + id + " não encontrada");
        }

    }
}
