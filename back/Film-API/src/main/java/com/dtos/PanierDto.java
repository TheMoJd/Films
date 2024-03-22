package com.dtos;

import java.util.List;

public class PanierDto {

    private String _id;
    private Long user_id;
    private List<ItemDto> items;

    // Constructeur, Getters et Setters

    public PanierDto() {
    }

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

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    // Classe interne DTO pour les articles du panier
    public static class ItemDto {
        private Long movie_id;
        private int quantity;

        // Constructeur, Getters et Setters

        public ItemDto() {
        }

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
