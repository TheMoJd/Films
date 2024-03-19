package com.services;

import com.dtos.PanierDto;
import java.util.List;

public interface PanierService {

    /**
     * Sauvegarde un panier.
     */
    PanierDto savePanier(PanierDto panierDto);

    /**
     * Récupère un panier par son identifiant.
     */
    PanierDto getPanierById(String panierId);

    //PanierDto getPanierByUserId(Long user_id);

    /**
     * Supprime un panier par son identifiant.
     */
    boolean deletePanier(String panierId);

    /**
     * Récupère tous les paniers.
     */
    List<PanierDto> getAllPanier();

    /**
     * Ajoute un film au panier.
     */
    PanierDto addMovieToPanier(String panierId, Long movieId, int quantity);

    /**
     * Supprime un film du panier.
     */
    PanierDto removeMovieFromPanier(String panierId, Long movieId);

    /**
     * Met à jour la quantité d'un film dans le panier.
     */
    PanierDto updateMovieQuantity(String panierId, Long movieId, int quantity);

    /**
     * Calcule le prix total du panier.
     */
    double getTotalPrice(String panierId);

    /**
     * Met à jour un panier.
     */
    PanierDto updatePanier(PanierDto panierDto);
}
