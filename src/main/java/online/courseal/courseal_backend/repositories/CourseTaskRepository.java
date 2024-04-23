package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseTaskRepository extends JpaRepository<CourseTask, Integer> {
    Optional<CourseTask> findByCourseTaskId(Integer courseTaskId);

    Optional<CourseTask> findByCourse(Course course);
}
