package com.repositories;

import com.entities.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends MongoRepository<Location, String> {
    @Query("{ 'user_id' : ?0 }")

    List<Location> findByUserId(Long userId);
}
