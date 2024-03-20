package com.controllers;

import com.dtos.PanierDto;
import com.services.impl.PanierServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paniers")
public class PanierController {

    private final PanierServiceImpl panierService;

    public PanierController(PanierServiceImpl panierService) {
        this.panierService = panierService;
    }

    @GetMapping
    public ResponseEntity<List<PanierDto>> getAllPaniers() {
        List<PanierDto> paniers = panierService.getAllPanier();
        return new ResponseEntity<>(paniers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanierDto> getPanierById(@PathVariable String id) {
        PanierDto panier = panierService.getPanierById(id);
        System.out.println("on est la ");
        System.out.println(panier.getId());
        return new ResponseEntity<>(panier, HttpStatus.OK);
    }

    @PostMapping("/createPanier/{user_id}")
    public ResponseEntity<PanierDto> createPanier(@PathVariable Long user_id) {
        PanierDto newPanier = panierService.createPanier(user_id);
        return new ResponseEntity<>(newPanier, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PanierDto> updatePanier(@PathVariable String id, @RequestBody PanierDto panierDto) {
        panierDto.setId(id);
        PanierDto updatedPanier = panierService.updatePanier(panierDto);
        return new ResponseEntity<>(updatedPanier, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePanier(@PathVariable String id) {
        panierService.deletePanier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{panier_id}/films/{movie_id}")
    public ResponseEntity<?> removeMovieFromPanier(@PathVariable String panier_id, @PathVariable Long movie_id) {
        try {
            PanierDto updatedPanier = panierService.removeMovieFromPanier(panier_id, movie_id);
            return ResponseEntity.ok(updatedPanier);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/{panier_id}/films/{movie_id}/{quantity}")
    public ResponseEntity<?> addMovieToPanier(@PathVariable String panier_id, @PathVariable Long movie_id, @PathVariable int quantity) {
        try {
            PanierDto updatedPanier = panierService.addMovieToPanier(panier_id, movie_id, quantity);
            return ResponseEntity.ok(updatedPanier);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*@GetMapping("/panier/{userId}")
    public ResponseEntity<PanierDto> getPanierByUserId(@PathVariable Long userId) {
        PanierDto panier = panierService.getPanierByUserId(userId);

        return new ResponseEntity<>(panier, HttpStatus.OK);
    }*/
}
