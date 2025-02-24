package cz.upce.fei.nnpiacv.component;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        User user = new User(0L, "admin@upce.cz", "admin");

        if (!userRepository.existsById(user.getId())) {
            log.info("Admin user created {}", user);
            userRepository.save(user);
        }
    }
}
