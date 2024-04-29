package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.CourseLessonLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseLessonLectureRepository extends JpaRepository<CourseLessonLecture, Integer> {
    Optional<CourseLessonLectureRepository> findByCourseLessonLectureId(Integer courseLessonLectureId);
}
