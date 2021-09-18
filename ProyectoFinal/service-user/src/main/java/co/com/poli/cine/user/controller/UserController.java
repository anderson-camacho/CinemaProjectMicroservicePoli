package co.com.poli.cine.user.controller;

import co.com.poli.cine.user.dto.UserDTO;
import co.com.poli.cine.user.entities.User;
import co.com.poli.cine.user.services.UserService;
import co.com.poli.cine.user.utils.Format;
import co.com.poli.cine.user.utils.Response;
import co.com.poli.cine.user.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ResponseBuilder builder;

    @PostMapping
    public Response save(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed(Format.formatMessage((result)));
        }

        userService.save(user);

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();

        return builder.success(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(user);

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UserDTO> listUserDTO = new ArrayList<UserDTO>();

        for (User user : users) {
            listUserDTO.add(UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .lastName(user.getLastName())
                    .build());
        }

        return ResponseEntity.ok(listUserDTO);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return builder.success(null);
        }

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();

        return builder.success(userDTO);
    }
}
