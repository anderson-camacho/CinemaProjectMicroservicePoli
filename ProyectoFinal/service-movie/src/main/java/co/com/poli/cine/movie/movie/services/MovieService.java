package co.com.poli.cine.movie.movie.services;

import co.com.poli.cine.movie.movie.entities.Movie;

import java.util.List;

public interface MovieService {
    void save(Movie movie);
    void delete(Movie movie);
    List<Movie> findAll();
    Movie findById(Long id);
}
