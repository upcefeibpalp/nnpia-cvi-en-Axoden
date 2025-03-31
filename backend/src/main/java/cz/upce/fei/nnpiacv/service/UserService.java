package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.dto.UserUpdateDTO;
import cz.upce.fei.nnpiacv.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Metoda pro aktualizaci uživatele
    public Optional<User> updateUser(Long id, UserUpdateDTO updateDTO) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setEmail(updateDTO.getEmail());
            existingUser.setPassword(updateDTO.getPassword());
            existingUser.setActive(updateDTO.getActive());
            return userRepository.save(existingUser);
        });
    }
}
