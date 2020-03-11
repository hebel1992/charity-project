package pl.coderslab.charityproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charityproject.models.Role;
import pl.coderslab.charityproject.models.User;
import pl.coderslab.charityproject.repositories.RoleRepository;
import pl.coderslab.charityproject.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}
