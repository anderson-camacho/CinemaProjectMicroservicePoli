package co.com.poli.cine.user.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO implements Serializable {
    private Long id;
    private String name;
    private String lastName;
}
