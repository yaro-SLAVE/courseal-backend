package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseMaintainerRepository extends JpaRepository<CourseMaintainer, Integer> {
    Optional<CourseMaintainer> findByCourseMaintainerId(Integer courseMaintainerId);

    Optional<CourseMaintainer> findByUser(User user);

    Optional<CourseMaintainer> findByCourse(Course course);
}
