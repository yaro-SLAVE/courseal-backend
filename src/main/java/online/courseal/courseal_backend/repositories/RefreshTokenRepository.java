package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.RefreshToken;
import online.courseal.courseal_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    @Modifying
    int deleteByUser(User user);
}
