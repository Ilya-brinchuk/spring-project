package com.spring.dao;

import com.spring.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    void add(User user);

    List<User> getAll();

    Optional<User> get(Long id);
}
