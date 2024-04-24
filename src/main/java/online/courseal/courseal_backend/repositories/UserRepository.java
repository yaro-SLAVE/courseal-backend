package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserTag(String userTag);

    Optional<User> findById(Integer userId);

    boolean existsByUserTag(String userTag);

    List<User> findByUserTagLike(String userTag);
}
