package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.domain.User;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final Map<Long, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        User user = new User(0L, "pepa@upce.cz", "lmaoxd");
        User user1 = new User(1L, "lojza@upce.cz", "kekw");

        users.put(user.getId(), user);
        users.put(user1.getId(), user1);
    }

    public Collection<User> findUsers() {
        return users.values().stream().toList();
    }

    /*
    public UserService() {
        User user = new User(0L, "pepa@upce.cz", "lmaoxd");
        User user1 = new User(1L, "lojza@upce.cz", "kekw");

        users.put(user.getId(), user);
        users.put(user1.getId(), user1);
    }
    */

    public String findUser() {
        logger.info("User created: {}", users.get(0L).toString());
        return users.get(0L).toString();
    }

    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(users.get(id));
    }
}
