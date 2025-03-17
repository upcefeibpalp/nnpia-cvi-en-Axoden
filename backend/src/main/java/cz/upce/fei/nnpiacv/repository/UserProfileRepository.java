package cz.upce.fei.nnpiacv.repository;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUser(User user);
}
