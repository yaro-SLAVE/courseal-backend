package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseLessonRepository extends JpaRepository<CourseLesson, Integer> {
    Optional<CourseLesson> findByCourseLessonId(Integer courseLessonId);

    List<CourseLesson> findByLessonLevelAndCourse(Integer lessonLevel, Course course);
}
