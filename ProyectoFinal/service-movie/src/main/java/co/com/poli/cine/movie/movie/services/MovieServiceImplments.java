package co.com.poli.cine.movie.movie.services;

import co.com.poli.cine.movie.movie.entities.Movie;
import co.com.poli.cine.movie.movie.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImplments implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public void save(Movie movie) {

        movieRepository.save(movie);
    }

    @Override
    public void delete(Movie movie) {

        movieRepository.delete(movie);
    }

    @Override
    public List<Movie> findAll() {

        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {

        return movieRepository.findById(id).orElse(null);
    }
}
