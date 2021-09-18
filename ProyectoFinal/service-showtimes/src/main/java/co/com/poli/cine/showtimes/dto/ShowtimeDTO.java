package co.com.poli.cine.showtimes.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ShowtimeDTO implements Serializable {
    private Long id;
    private Date date;
    private Long[] movies;
}
