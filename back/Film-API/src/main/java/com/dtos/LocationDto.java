package com.dtos;

import java.util.List;

public class LocationDto {

    private String id;
    private Long user_id;
    private String panier_id;
    private List<RentalDto> rentals;

    // Constructeurs, getters et setters

    public LocationDto() {
    }

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

    public List<RentalDto> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalDto> rentals) {
        this.rentals = rentals;
    }

    public static class RentalDto {
        private Long movie_id;
        private String rental_date;
        private String return_date;
        private String etat;

        // Constructeurs, getters et setters
        public RentalDto() {
        }

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
    }
}
