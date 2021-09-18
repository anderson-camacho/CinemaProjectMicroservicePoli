package co.com.poli.cine.showtimes.models;

import lombok.Data;

@Data
public class Movies {
    private Long id;
    private String title;
    private String director;
    private int rating;
}

