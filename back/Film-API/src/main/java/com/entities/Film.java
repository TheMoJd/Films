package com.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "films") // Assurez-vous que le nom de la table correspond à votre schéma de base de données
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;

    @Column(name = "name") // Correspond au nom du film
    private String name;

    @Column(name = "release_date") // Correspond à la date de sortie du film
    @Temporal(TemporalType.DATE) // Utiliser TemporalType.TIMESTAMP si vous avez besoin de l'heure également
    private Date release_date;

    @Column(name = "description") // Correspond à la description du film
    private String description;



    @Column(name = "url") // Correspond à la description du film
    private String url;

    // Getters et Setters

    public Long getId() {
        return movie_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
