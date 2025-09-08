package org.example.mapper;

import org.example.domain.User;
import java.util.List;

public interface UserMapper {
    User getUserById(Long id);
    List<User> getAllUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
