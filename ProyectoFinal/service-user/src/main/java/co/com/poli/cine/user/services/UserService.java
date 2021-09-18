package co.com.poli.cine.user.services;

import co.com.poli.cine.user.entities.User;

import java.util.List;

public interface UserService {
    void save(User user);

    void delete(User user);

    List<User> findAll();

    User findById(Long id);
}
