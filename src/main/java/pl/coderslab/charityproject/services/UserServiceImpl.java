package pl.coderslab.charityproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charityproject.EntityNotFoundException;
import pl.coderslab.charityproject.models.Role;
import pl.coderslab.charityproject.models.User;
import pl.coderslab.charityproject.repositories.RoleRepository;
import pl.coderslab.charityproject.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, User.class.getSimpleName()));
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user, String role, Boolean encodePassword) {
        if (encodePassword) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setEnabled(1);
        user.setBlocked(false);
        if (role.equals("admin")) {
            Role userRole = roleRepository.findByName("ROLE_ADMIN");
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        } else if (role.equals("user")) {
            Role userRole = roleRepository.findByName("ROLE_USER");
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        } else {
            user.setBlocked(true);
            user.setRoles(new HashSet<Role>(Arrays.asList()));
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAdmins() {
        return userRepository.findByRoles_Id(1L);
    }

    @Override
    public List<User> findUsers() {
        List<User> allUsers = userRepository.findByRoles_Id(2L);
        List<User> blockedUsers = userRepository.findBlockedUsers();

        allUsers.addAll(blockedUsers);

        return allUsers;
    }
}
