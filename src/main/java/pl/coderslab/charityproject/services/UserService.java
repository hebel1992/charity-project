package pl.coderslab.charityproject.services;

import pl.coderslab.charityproject.models.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByUserName(String username);

    User findByEmail(String email);

    void saveUser(User user);

    void saveAdmin(User admin);

    void deleteUser(User user);

    List<User> findAdmins();

    List<User> findUsers();
}
