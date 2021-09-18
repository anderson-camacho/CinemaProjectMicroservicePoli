package co.com.poli.cine.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO implements Serializable {

    private long id;
    private long userId;
    private long showTimeId;
    private List<Object> movies;
}
