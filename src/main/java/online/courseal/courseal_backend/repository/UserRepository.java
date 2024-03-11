package online.courseal.courseal_backend.repository;

import online.courseal.courseal_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserTag(String userTag);
    boolean existsByEmail(String email);
    boolean existsByUserTag(String userTag);
}
