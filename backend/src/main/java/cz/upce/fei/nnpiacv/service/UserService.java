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
    private final Map<Long, User> users = new HashMap<>();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        /*
        User user = new User(0L, "pepa@upce.cz", "lmaoxd");
        User user1 = new User(1L, "lojza@upce.cz", "kekw");

        users.put(user.getId(), user);
        users.put(user1.getId(), user1);
         */
    }

    public Collection<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        log.info("ziskan uzivael {}", user.get());

        return user.orElse(null);
    }
    /*
    public UserService() {
        User user = new User(0L, "pepa@upce.cz", "lmaoxd");
        User user1 = new User(1L, "lojza@upce.cz", "kekw");

        users.put(user.getId(), user);
        users.put(user1.getId(), user1);
    }
    */

    /*
    public String findUser() {
        logger.info("User created: {}", users.get(0L).toString());
        return users.get(0L).toString();
    }
     */

    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
