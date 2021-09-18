package co.com.poli.cine.movie.movie.controller;

import co.com.poli.cine.movie.movie.dto.MovieDTO;
import co.com.poli.cine.movie.movie.entities.Movie;
import co.com.poli.cine.movie.movie.services.MovieService;
import co.com.poli.cine.movie.movie.utils.Format;
import co.com.poli.cine.movie.movie.utils.Response;
import co.com.poli.cine.movie.movie.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ResponseBuilder builder;

    @PostMapping
    public Response save(@Valid @RequestBody Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed((Format.formatMessage(result)));
        }

        movieService.save(movie);

        MovieDTO movieDTO = MovieDTO.builder().id(movie.getId()).title(movie.getTitle()).director(movie.getDirector()).rating(movie.getRating()).build();
        return builder.success(movieDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        movieService.delete(movie);

        MovieDTO movieDTO = MovieDTO.builder()
                .id(movie.getId()).title(movie.getTitle()).director(movie.getDirector()).rating(movie.getRating()).build();

        return ResponseEntity.ok(movieDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll() {
        List<Movie> movies = movieService.findAll();
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<MovieDTO> listMovieDTO = new ArrayList<MovieDTO>();

        for (Movie movie : movies) {
            listMovieDTO.add(MovieDTO.builder()
                    .title(movie.getTitle())
                    .director(movie.getDirector())
                    .rating(movie.getRating())
                    .build());
        }

        return ResponseEntity.ok(listMovieDTO);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return builder.success(null);
        }

        MovieDTO userDTO = MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .rating(movie.getRating())
                .build();

        return builder.success(userDTO);
    }


}
