package cz.upce.fei.nnpiacv.repository;

import cz.upce.fei.nnpiacv.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
