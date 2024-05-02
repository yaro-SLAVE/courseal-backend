package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.CourseLessonTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseLessonTaskRepository extends JpaRepository<CourseLessonTask, Integer> {
    Optional<CourseLessonTask> findByCourseLessonTaskId(Integer courseLessonTaskId);
}
