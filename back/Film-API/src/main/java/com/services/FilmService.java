package com.services;

import com.dtos.FilmDto;

import java.util.List;

public interface FilmService {
    /**
     * Sauve a dog
     */
    FilmDto saveFilm(FilmDto filmDto);

    /**
     * Get a dog by it's id
     */
    FilmDto getFilmById(Long filmId);

    /**
     * Delete a dog by it's id
     */
    boolean deleteFilm(Long filmId);

    /**
     * Get all the dogs
     */
    List<FilmDto> getAllFilm();


}
