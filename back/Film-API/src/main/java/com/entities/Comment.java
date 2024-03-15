package com.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column(name = "movie_id")
    private Long movieId;

    // Supposant que vous avez une entité User pour gérer les utilisateurs
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "corps", nullable = false, length = 500)
    private String corps;


    // Getters et Setters

    public Long getId() {
        return comment_id;
    }

    public void setId(Long comment_id) {
        this.comment_id = comment_id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }
}
