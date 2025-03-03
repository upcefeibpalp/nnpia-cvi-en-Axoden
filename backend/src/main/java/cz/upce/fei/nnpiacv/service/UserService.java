package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Metoda pro získání všech uživatelů
    public Collection<User> findUsers() {
        return userRepository.findAll();
    }

    // Metoda pro vyhledání uživatele dle id
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // Metoda pro vyhledání uživatele dle emailu
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // NOVÁ metoda pro přidání uživatele
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}