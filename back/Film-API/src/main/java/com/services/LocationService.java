package com.services;

import com.dtos.LocationDto;
import java.util.List;

public interface LocationService {

    LocationDto getLocationById(String id);

    List<LocationDto> findAllLocations();

    LocationDto createLocation(LocationDto locationDto);

    LocationDto updateLocation(String id, LocationDto locationDto);

    void deleteLocation(String id);

    // Les méthodes de conversion peuvent rester privées dans l'implémentation du service
    // et n'ont pas besoin d'être exposées dans l'interface.
}
