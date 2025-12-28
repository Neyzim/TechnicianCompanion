package com.example.TechnicianCompanion.cities.controller;

import com.example.TechnicianCompanion.cities.dto.CitiesDTO;
import com.example.TechnicianCompanion.cities.models.Cities;
import com.example.TechnicianCompanion.cities.service.CitiesService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Cria uma nova Cidade", description = "Cria uma nova cidade e salva no Banco de Dados")
    public ResponseEntity<CitiesDTO> createCity(@RequestBody CitiesDTO city){
        CitiesDTO createdCity = citiesService.createCity(city);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdCity);
    }

    @GetMapping(value = "/list/{id}")
    @Operation(summary = "Encontra uma cidade por ID", description = "Busca uma cidade por ID e retorna seus detalhes")
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
    @Operation(summary = "Deleta uma cidade")
    public ResponseEntity<String> deleteCityByID(@PathVariable Long id){
        if (citiesService.findCityById(id).isPresent()){
            citiesService.DeleteCityById(id);
            return ResponseEntity.ok("Cidade Deletada: " + id);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A codade com i ID " + id + " não foi encotrada!");
        }
    }

    @GetMapping(value = "/list")
    @Operation(summary = "Lista todas as cidades")
    public ResponseEntity<List<CitiesDTO>> listAllCities(){
        List<CitiesDTO> citiesList = citiesService.listAllCities();
        return ResponseEntity.ok(citiesList);
    }

    @PatchMapping(value = "/update/{id}")
    @Operation(summary = "Atualiza uma cidade")
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
