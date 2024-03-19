package com.repositories;

import com.entities.Panier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PanierRepository extends MongoRepository<Panier, String> {
   // Panier findByUserId(Long user_id);

}
