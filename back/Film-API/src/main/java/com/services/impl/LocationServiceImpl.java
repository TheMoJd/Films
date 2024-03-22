package com.services.impl;

import com.dtos.LocationDto;
import com.entities.Location;
import com.repositories.LocationRepository;
import com.services.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    @Override
    public LocationDto getLocationById(String id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Film not found with id " + id));
        return convertToDto(location);    }

    @Override
    public List<LocationDto> findAllLocations() {
        return locationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        Location location = convertToEntity(locationDto);
        location = locationRepository.save(location);
        return convertToDto(location);
    }

    @Override
    public LocationDto updateLocation(String id, LocationDto locationDto) {
        return null;
    }

    @Override
    public void deleteLocation(String id) {
        locationRepository.deleteById(id);

    }

    public List<LocationDto> getLocationByUserId(Long user_id) {
        return locationRepository.findByUserId(user_id).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LocationDto convertToDto(Location location) {
        LocationDto locationDto = new LocationDto();
        BeanUtils.copyProperties(location, locationDto);
        if(location.getRentals() != null) {
            List<LocationDto.RentalDto> rentalDtos = location.getRentals().stream()
                    .map(this::convertRentalToDto)
                    .collect(Collectors.toList());
            locationDto.setRentals(rentalDtos);
        }
        return locationDto;
    }

    private Location convertToEntity(LocationDto locationDto) {
        Location location = new Location();
        BeanUtils.copyProperties(locationDto, location);
        return location;
    }



    private LocationDto.RentalDto convertRentalToDto(Location.Rental rental) {
        LocationDto.RentalDto rentalDto = new LocationDto.RentalDto();
        BeanUtils.copyProperties(rental, rentalDto);
        return rentalDto;
    }
}
