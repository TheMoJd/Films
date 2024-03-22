package com.controllers;

import com.dtos.LocationDto;
import com.services.impl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rentals")
public class LocationsController {


    private final LocationServiceImpl locationService;

    public LocationsController(LocationServiceImpl locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        List<LocationDto> locations = locationService.findAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        LocationDto newLocation = locationService.createLocation(locationDto);
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable String id) {
        LocationDto location = locationService.getLocationById(id);
        if (location != null) {
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/locationsByUserId/{user_id}")
    public ResponseEntity<List<LocationDto>> getLocationsByUserId(@PathVariable Long user_id) {
        List<LocationDto> locations = locationService.getLocationByUserId(user_id);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    /*@PostMapping("/addLocation/{locationData}")
    public ResponseEntity<LocationDto> addLocation(@PathVariable String locationData) {
        LocationDto newLocation = locationService.createLocation(locationData);
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }
*/
    // Ajoutez ici d'autres méthodes selon vos besoins
}
