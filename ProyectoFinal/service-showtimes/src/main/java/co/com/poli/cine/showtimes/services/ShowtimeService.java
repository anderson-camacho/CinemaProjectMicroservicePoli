package co.com.poli.cine.showtimes.services;

import co.com.poli.cine.showtimes.entities.Showtime;

import java.util.List;

public interface ShowtimeService {
    void save(Showtime showtime);

    void delete(Showtime showtime);

    List<Showtime> findAll();

    Showtime findById(Long id);
}
