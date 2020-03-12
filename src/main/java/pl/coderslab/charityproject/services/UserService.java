package pl.coderslab.charityproject.services;

import pl.coderslab.charityproject.models.User;

public interface UserService {
    User findByUserName(String username);

    User findByEmail(String email);

    void saveUser(User user);
}
