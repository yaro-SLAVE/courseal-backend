package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseMaintainerRepository extends JpaRepository<CourseMaintainer, Integer> {
    Optional<CourseMaintainer> findByCourseMaintainerId(Integer courseMaintainerId);
    Optional<CourseMaintainer> findByUser(User user);
}
