package com.services.impl;

import com.dtos.FilmDto;
import com.entities.Film;
import com.repositories.FilmRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service("filmService")
public class FilmServiceImpl {

    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    public List<FilmDto> findAll() {
        return filmRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public FilmDto findById(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Film not found with id " + id));
        return convertToDto(film);
    }

    public FilmDto save(FilmDto filmDto) {
        Film film = convertToEntity(filmDto);
        film = filmRepository.save(film);
        return convertToDto(film);
    }

    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    private FilmDto convertToDto(Film film) {
        FilmDto filmDto = new FilmDto();
        BeanUtils.copyProperties(film, filmDto);
        return filmDto;
    }

    private Film convertToEntity(FilmDto filmDto) {
        Film film = new Film();
        BeanUtils.copyProperties(filmDto, film);
        return film;
    }
}
