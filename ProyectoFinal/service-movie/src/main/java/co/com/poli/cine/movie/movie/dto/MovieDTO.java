package co.com.poli.cine.movie.movie.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO implements Serializable {
    private Long id;
    private String title;
    private String director;
    private int rating;
}
