package co.com.poli.cine.showtimes.controller;

import co.com.poli.cine.showtimes.dto.ShowtimeDTO;
import co.com.poli.cine.showtimes.entities.Showtime;
import co.com.poli.cine.showtimes.services.ShowtimeService;
import co.com.poli.cine.showtimes.utils.Format;
import co.com.poli.cine.showtimes.utils.Response;
import co.com.poli.cine.showtimes.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/showtime")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    private final ResponseBuilder builder;

    @PostMapping
    public Response save(@Valid @RequestBody Showtime showtime, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed(Format.formatMessage((result)));
        }

        showtimeService.save(showtime);

        ShowtimeDTO showtimeDTO = ShowtimeDTO.builder().id(showtime.getId()).date(showtime.getDate()).build();

        return builder.success(showtimeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Showtime showtime = showtimeService.findById(id);
        if (showtime == null) {
            return ResponseEntity.notFound().build();
        }
        showtimeService.delete(showtime);

        ShowtimeDTO showtimeDTO = ShowtimeDTO.builder().id(showtime.getId()).date(showtime.getDate()).build();

        return ResponseEntity.ok(showtimeDTO);
    }

    @GetMapping
    public ResponseEntity<List<ShowtimeDTO>> findAll() {
        List<Showtime> users = showtimeService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ShowtimeDTO> listShowtimeDTO = new ArrayList<ShowtimeDTO>();

        for (Showtime showtime : users) {
            listShowtimeDTO.add(ShowtimeDTO.builder().id(showtime.getId()).date(showtime.getDate()).build());
        }

        return ResponseEntity.ok(listShowtimeDTO);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id) {
        Showtime showtime = showtimeService.findById(id);
        if (showtime == null) {
            return builder.success(null);
        }

        ShowtimeDTO showtimeDTO = ShowtimeDTO.builder()
                .id(showtime.getId()).date(showtime.getDate()).build();

        return builder.success(showtimeDTO);
    }
}
