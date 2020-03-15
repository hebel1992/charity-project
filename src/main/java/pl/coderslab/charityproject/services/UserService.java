package pl.coderslab.charityproject.services;

import pl.coderslab.charityproject.models.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByUserName(String username);

    User findByEmail(String email);

    void saveUser(User user, String role, Boolean encodePassword);

    void deleteUser(User user);

    List<User> findAdmins();

    List<User> findUsers();
}
