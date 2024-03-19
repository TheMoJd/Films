package com.services.impl;

import com.dtos.PanierDto;
import com.entities.Panier;
import com.repositories.PanierRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.services.PanierService;

import java.util.List;
import java.util.stream.Collectors;

@Service("panierService")
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;

    public PanierServiceImpl(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    @Override
    public PanierDto savePanier(PanierDto panierDto) {
        Panier panier = convertToEntity(panierDto);
        panier = panierRepository.save(panier);
        return convertToDto(panier);
    }

    @Override
    public PanierDto getPanierById(String panierId) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier not found with id " + panierId));

        return convertToDto(panier);
    }

    /*@Override
    public PanierDto getPanierByUserId(Long user_id) {
        Panier panier = panierRepository.findByUserId(user_id);
        return convertToDto(panier);
    }*/


    @Override
    public boolean deletePanier(String panierId) {
        if (!panierRepository.existsById(panierId)) {
            throw new RuntimeException("Panier not found with id " + panierId);
        }
        panierRepository.deleteById(panierId);
        return true;
    }

    @Override
    public List<PanierDto> getAllPanier() {
        List<PanierDto> paniers = panierRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        System.out.println(panierRepository.findAll());
        System.out.println("Liste des paniers : ");
        System.out.println(paniers);
        return paniers;
    }


    @Override
    public PanierDto addMovieToPanier(String panierId, Long movieId, int quantity) {
        // Trouver le panier par son ID
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier not found with id " + panierId));

        // Ajouter le film au panier
        Panier.Item newItem = new Panier.Item();
        newItem.setMovie_id(movieId);
        newItem.setQuantity(quantity);

        // Vérifier si le film est déjà dans le panier
        boolean exists = false;
        for (Panier.Item item : panier.getItems()) {
            if (item.getMovie_id().equals(movieId)) {
                item.setQuantity(item.getQuantity() + quantity); // Mettre à jour la quantité si le film existe déjà
                exists = true;
                break;
            }
        }
        if (!exists) {
            panier.getItems().add(newItem); // Ajouter un nouvel item si le film n'existe pas
        }

        // Sauvegarder le panier mis à jour
        Panier updatedPanier = panierRepository.save(panier);

        // Convertir en DTO et renvoyer
        return convertToDto(updatedPanier);
    }

    @Override
    public PanierDto removeMovieFromPanier(String panier_id, Long movieId) {
        // Trouver le panier par son ID
        Panier panier = panierRepository.findById(panier_id)
                .orElseThrow(() -> new RuntimeException("Panier not found with id " + panier_id));

        // Supprimer le film du panier
        panier.setItems(panier.getItems().stream()
                .filter(item -> !item.getMovie_id().equals(movieId)) // Conserver tous les éléments sauf celui à supprimer
                .collect(Collectors.toList()));

        // Sauvegarder le panier mis à jour
        Panier updatedPanier = panierRepository.save(panier);

        // Convertir en DTO et renvoyer
        return convertToDto(updatedPanier);
    }


    @Override
    public PanierDto updateMovieQuantity(String panierId, Long movieId, int quantity) {
        return null;
    }

    @Override
    public double getTotalPrice(String panierId) {
        return 0;
    }

    public PanierDto updatePanier(PanierDto panierDto) {
        Panier panier = convertToEntity(panierDto);
        panier = panierRepository.save(panier);
        return convertToDto(panier);
    }

    private PanierDto convertToDto(Panier panier) {
        PanierDto panierDto = new PanierDto();
        BeanUtils.copyProperties(panier, panierDto);
        if (panier.getItems() != null) {
            List<PanierDto.ItemDto> itemDtos = panier.getItems().stream()
                    .map(this::convertItemToDto) // Vous devez implémenter cette méthode
                    .collect(Collectors.toList());
            panierDto.setItems(itemDtos);
        }
        return panierDto;
    }

    private PanierDto.ItemDto convertItemToDto(Panier.Item item) {
        PanierDto.ItemDto itemDto = new PanierDto.ItemDto();
        BeanUtils.copyProperties(item, itemDto);
        return itemDto;
    }

    private Panier convertToEntity(PanierDto panierDto) {
        Panier panier = new Panier();
        BeanUtils.copyProperties(panierDto, panier);
        return panier;
    }
}
