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

import java.util.Optional;

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
        // Look up the admin user by email.
        User user = userRepository.findByEmail("admin@upce.cz").orElse(null);
        if (user == null) {
            user = new User("admin@upce.cz", "admin");
            user = userRepository.saveAndFlush(user);
            log.info("Admin user created: {}", user);
        } else {
            log.info("Admin user already exists: {}", user);
        }

        // Check if a profile already exists for this user.
        Optional<UserProfile> existingProfile = userProfileRepository.findByUser(user);
        if (existingProfile.isEmpty()) {
            UserProfile profile = new UserProfile();
            profile.setBio("Profil administrátora");
            profile.setUser(user);
            profile = userProfileRepository.save(profile);
            log.info("User profile created: {}", profile);
        } else {
            log.info("User profile already exists: {}", existingProfile.get());
        }
    }


}
