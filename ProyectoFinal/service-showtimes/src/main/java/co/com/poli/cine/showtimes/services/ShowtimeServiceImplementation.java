package co.com.poli.cine.showtimes.services;

import co.com.poli.cine.showtimes.client.MovieClient;
import co.com.poli.cine.showtimes.entities.ItemsShowtimes;
import co.com.poli.cine.showtimes.entities.Showtime;
import co.com.poli.cine.showtimes.models.Movies;
import co.com.poli.cine.showtimes.repositories.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImplementation implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieClient movieClient;

    @Override
    public void save(Showtime showtime) {
        showtimeRepository.save(showtime);
    }

    @Override
    public void delete(Showtime showtime) {
        showtimeRepository.delete(showtime);
    }

    @Override
    public List<Showtime> findAll() {
        return null;
    }

    @Override
    public Showtime findById(Long id) {
        Showtime showtime = showtimeRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        List<ItemsShowtimes> listMovies = showtime.getMovies().stream().map(
                itemsShowtime -> {
                    Movies movies = modelMapper.map(movieClient.findById(itemsShowtime.getMovieId()).getData(), Movies.class);
                    itemsShowtime.setMovies(movies);
                    return itemsShowtime;
                }).collect(Collectors.toList());
        return showtimeRepository.findById(id).orElse(null);
    }
}
