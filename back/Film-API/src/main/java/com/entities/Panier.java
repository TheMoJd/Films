package com.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "paniers") // Assure que le nom de la collection correspond à votre schéma de base de données MongoDB
public class Panier {

    @Id
    private String _id; // MongoDB utilise des ObjectId, mais ils sont représentés sous forme de String dans le code

    private Long user_id; // L'identifiant de l'utilisateur associé au panier

    private List<Item> items; // La liste des articles dans le panier

    // Getters et Setters

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }



    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // Classe interne pour représenter les articles du panier
    public static class Item {
        private Long movie_id; // L'identifiant du film
        private int quantity; // La quantité

        // Getters et Setters

        public Long getMovie_id() {
            return movie_id;
        }

        public void setMovie_id(Long movie_id) {
            this.movie_id = movie_id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
