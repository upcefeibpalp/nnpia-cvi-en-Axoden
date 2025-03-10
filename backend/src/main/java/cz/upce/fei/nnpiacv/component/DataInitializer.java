package cz.upce.fei.nnpiacv.component;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.domain.UserProfile;
import cz.upce.fei.nnpiacv.repository.UserRepository;
import cz.upce.fei.nnpiacv.repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    public DataInitializer(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        User user = new User("admin@upce.cz", "admin");

        if (!userRepository.existsById(user.getId())) {
            log.info("Admin user created {}", user);
            user = userRepository.saveAndFlush(user);
        }

        // Vytvoření profilu, který bude ve vztahu s již existujícím uživatelem
        UserProfile profile = new UserProfile();
        profile.setBio("Profil administrátora");
        profile = userProfileRepository.save(profile);
        profile.setUser(user);

        log.info("User profile created: {}", profile);
        userProfileRepository.save(profile);
    }
}
