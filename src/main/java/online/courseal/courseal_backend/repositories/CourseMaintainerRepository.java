package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.CourseMaintainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseMaintainerRepository extends JpaRepository<CourseMaintainer, Integer> {
    Optional<Integer> findByCourseMaintainerId(Integer courseMaintainerId);

    Optional<Integer> findByCourseID(Integer courseId);

    Optional<Integer> findByUserId(Integer userId);
}
