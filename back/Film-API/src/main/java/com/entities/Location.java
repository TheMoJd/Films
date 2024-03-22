package com.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "rentals")
public class Location {

    @Id
    private String id;
    private Long user_id;
    private String panier_id;
    private List<Rental> rentals;

    // Classe interne et getters et setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(String panier_id) {
        this.panier_id = panier_id;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public static class Rental {
        private Long movie_id;
        private String rental_date;
        private String return_date;
        private String etat;

        public Long getMovie_id() {
            return movie_id;
        }

        public void setMovie_id(Long movie_id) {
            this.movie_id = movie_id;
        }

        public String getRental_date() {
            return rental_date;
        }

        public void setRental_date(String rental_date) {
            this.rental_date = rental_date;
        }

        public String getReturn_date() {
            return return_date;
        }

        public void setReturn_date(String return_date) {
            this.return_date = return_date;
        }

        public String getEtat() {
            return etat;
        }

        public void setEtat(String etat) {
            this.etat = etat;
        }
// Getters et setters
    }
}
