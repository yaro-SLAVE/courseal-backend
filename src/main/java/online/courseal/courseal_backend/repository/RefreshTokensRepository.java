package online.courseal.courseal_backend.repository;

import online.courseal.courseal_backend.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokensRepository extends JpaRepository<RefreshToken, Integer> {
}
