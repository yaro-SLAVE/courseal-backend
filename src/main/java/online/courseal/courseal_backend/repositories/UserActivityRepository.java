package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.models.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
    Optional<UserActivity> findByUserActivityId(Integer userActivityId);

    List<UserActivity> findByUserAndDay(User user, LocalDate day);

    Boolean existsByUserAndDay(User user, LocalDate day);
}
