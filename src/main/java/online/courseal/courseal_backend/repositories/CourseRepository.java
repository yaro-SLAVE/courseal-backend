package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByCourseId(Integer courseId);
}
